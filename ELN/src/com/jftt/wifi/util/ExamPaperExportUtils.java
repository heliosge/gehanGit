/*
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2011
 * FileName: ExamPaperExportUtils.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2015/09/02        | JFTT)wangyifeng    | original version
 */
package com.jftt.wifi.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.util.Assert;

import com.jftt.wifi.bean.exam.PaperBean;
import com.jftt.wifi.bean.exam.PaperQuestionBean;
import com.jftt.wifi.bean.exam.QuestionBean;
import com.jftt.wifi.bean.exam.vo.QuestionListItemVo;
import com.jftt.wifi.service.ExamQuestionService;

/**
 * @author JFTT)wangyifeng
 * class name:ExamPaperExportUtils <BR>
 * class description: 试卷导出工具类 <BR>
 * Remark: <BR>
 * @version 1.00 2015/09/02
 */
public class ExamPaperExportUtils {
	private static final String FONT_FAMILY = "微软雅黑";
	private static String[] optionName = new String[26];
	private static Map<Integer, String> displayTypeStrMap = new HashMap<Integer, String>();
	static {
		for (int i = 0; i < 26; i++) {
			optionName[i] = Character.toString((char) (0x41 + i));
		}

		displayTypeStrMap.put(1, "主观题");
		displayTypeStrMap.put(2, "单选题");
		displayTypeStrMap.put(3, "多选题");
		displayTypeStrMap.put(4, "判断题");
		displayTypeStrMap.put(5, "填空题");
		displayTypeStrMap.put(6, "组合题");
		displayTypeStrMap.put(7, "多媒体题");
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: exportAsWord <BR>
	 * Description: 根据传入的Paper对象生成word对象，用于Word格式的导出 <BR>
	 * Remark: <BR>
	 * @param paperBean
	 * @return  XWPFDocument<BR>
	 */
	public static XWPFDocument exportAsWord(PaperBean paperBean) {
		XWPFDocument doc = new XWPFDocument();

		if (paperBean.getOrganizingMode() == 2) {
			// 暂不支持随机试卷的导出
			return doc;
		}

		XWPFParagraph p = doc.createParagraph();
		XWPFRun rTitle = p.createRun();
		rTitle.setBold(true);
		setFontFamily(rTitle);
		rTitle.setText(paperBean.getTitle());
		rTitle.addCarriageReturn();
		XWPFRun rScore = p.createRun();
		rScore.setBold(false);
		setFontFamily(rScore);
		rScore.setText(String.format("考试详情：总分：%d", paperBean.getTotalScore()));
		rScore.addCarriageReturn();

		for (int i = 0; i < paperBean.getPaperQuestionList().size(); i++) {
			PaperQuestionBean paperQuestionBean = paperBean
					.getPaperQuestionList().get(i);
			QuestionBean q = paperBean.getPaperQuestionList().get(i)
					.getQuestionBean();
			XWPFRun r1 = p.createRun();
			r1.setBold(true);
			setFontFamily(r1);
			String content = q.getContent();
			if (q.getDisplayType() == 7) {
				content = "不支持多媒体题的导出";
			}
			r1.setText(String.format("(%d分)%d.%s (%s)",
					paperQuestionBean.getScore(), i + 1, setNull(content),
					displayTypeStrMap.get(q.getDisplayType())));
			r1.addCarriageReturn();

			XWPFRun r2 = p.createRun();
			setFontFamily(r2);
			writeQuestionDetail(paperQuestionBean, q, r2);
		}
		return doc;
	}
	
	/**
	 * @author JFTT)wj
	 * 根据试题list 导出题目
	 */
	public static XWPFDocument exporQuestiontAsWord(ExamQuestionService questionService, List<QuestionListItemVo> questionList) {
		XWPFDocument doc = new XWPFDocument();

		XWPFParagraph p = doc.createParagraph();
		
		/*XWPFRun rTitle = p.createRun();
		rTitle.setBold(true);
		setFontFamily(rTitle);
		rTitle.setText(paperBean.getTitle());
		rTitle.addCarriageReturn();*/
		
		/*XWPFRun rScore = p.createRun();
		rScore.setBold(false);
		setFontFamily(rScore);
		rScore.setText(String.format("考试详情：总分：%d", paperBean.getTotalScore()));
		rScore.addCarriageReturn();*/

		for (int i = 0; i <questionList.size(); i++) {
			QuestionListItemVo ques = questionList.get(i);
			
			QuestionBean q = questionService.getQuestion(ques.getId());
			
			XWPFRun r1 = p.createRun();
			r1.setBold(true);
			setFontFamily(r1);
			String content = q.getContent();
			if (q.getDisplayType() == 7) {
				content = "不支持多媒体题的导出";
			}
			r1.setText(String.format("%d.%s (%s)", i + 1, setNull(content), displayTypeStrMap.get(q.getDisplayType())));
			r1.addCarriageReturn();

			XWPFRun r2 = p.createRun();
			setFontFamily(r2);
			writeQuestionDetail(null, q, r2);
		}
		return doc;
	}


	/**
	 * @author JFTT)wangyifeng
	 * Method name: writeQuestionDetail <BR>
	 * Description: 向XWPFRun写入问题详情（独立成函数，方便组合题情况下的递归调用） <BR>
	 * Remark: <BR>
	 * @param paperQuestionBean 组合题子试题情况时，该参数为null
	 * @param q
	 * @param r2  void<BR>
	 */
	private static void writeQuestionDetail(PaperQuestionBean paperQuestionBean, QuestionBean q, XWPFRun r2) {
		
		switch (q.getDisplayType()) {
		case 1:
			// do nothing
			break;
		case 2:
			// same as case 3
		case 3:
			for (int oIndex = 0; oIndex < q.getOptions().size(); oIndex++) {
				r2.setText(String.format("%s.%s", optionName[oIndex], setNull(q
						.getOptions().get(oIndex).getContent())));
				r2.addCarriageReturn();
			}
			break;
		case 4:
			// do nothing
			break;
		case 5:
			// do nothing
			break;
		case 6:
			String[] scoreList = new String[0];
			
			if(paperQuestionBean == null){
				//导出试题
				
			}else{
				//导出试卷
				Assert.notNull(paperQuestionBean.getScoreDetail());
				if (StringUtils.isNotBlank(paperQuestionBean.getScoreDetail())) {
					scoreList = StringUtils.split(
							paperQuestionBean.getScoreDetail(), ",");
				}
			}
			
			for (int oIndex = 0; oIndex < q.getSubQuestions().size(); oIndex++) {
				int score = 0;
				if (scoreList.length > oIndex) {
					score = Integer.parseInt(scoreList[oIndex]);
				}
				r2.setText(String.format(
						"(%d分)问题%d.%s (%s)",
						score,
						oIndex + 1,
						setNull(q.getSubQuestions().get(oIndex).getContent()),
						displayTypeStrMap.get(q.getSubQuestions().get(oIndex)
								.getDisplayType())));
				r2.addCarriageReturn();
				writeQuestionDetail(null, q.getSubQuestions().get(oIndex), r2);
			}
			break;
		case 7:
			// do nothing
			break;
		default:
			throw new RuntimeException(String.format(
					"不支持该试题类型，试题编号：%d，试题类型：%d。", q.getId(), q.getDisplayType()));
		}
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: setNull <BR>
	 * Description: 判空 <BR>
	 * Remark: <BR>
	 * @param content
	 * @return  String<BR>
	 */
	private static String setNull(String content) {
		return StringUtils.isNotBlank(content) ? content : "暂无";
	}

	/**
	 * @author JFTT)wangyifeng
	 * Method name: setFontFamily <BR>
	 * Description: 设置字体 <BR>
	 * Remark: <BR>
	 * @param r  void<BR>
	 */
	private static void setFontFamily(XWPFRun r) {
		r.setFontFamily(FONT_FAMILY);
	}
}
