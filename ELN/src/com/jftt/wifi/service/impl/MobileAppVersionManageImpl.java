package com.jftt.wifi.service.impl;

import com.jftt.wifi.bean.MobileAppVersionBean;
import com.jftt.wifi.common.Constant;
import com.jftt.wifi.dao.MobileAppVersionDaoMapper;
import com.jftt.wifi.service.MobileAppVersionManageService;
import com.jftt.wifi.util.JsonUtil;
import com.jftt.wifi.util.PropertyUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 16-3-18.
 */
@Service("mobileAppVersionManageService")
public class MobileAppVersionManageImpl implements MobileAppVersionManageService {

    @Autowired
    private MobileAppVersionDaoMapper mobileAppVersionDaoMapper;
    @Override
    public MobileAppVersionBean getById(Integer id) throws Exception {
        return mobileAppVersionDaoMapper.getById(id);
    }

    @Override
    public Integer checkName(MobileAppVersionBean bean) throws Exception {
        return mobileAppVersionDaoMapper.checkName(bean);
    }

    @Override
    public String uploadFile(MultipartFile file) {
         String commonPath ="mobileApp/uploadKL";
        String extendUrl;
        String genName;
        try {
            //保存在指定文件路径里
            String path = PropertyUtil.getConfigureProperties("UPLOAD_PATH");//拿到实际存放地址。D:/ELN/upload/
            //获取拼接地址
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            extendUrl = commonPath+"/"+df.format(new Date());
            path +=extendUrl;


            String fileName = file.getOriginalFilename();
            String catalog = "";
            String suffixName =fileName.substring(fileName.lastIndexOf(".")+1);

            genName = UUID.randomUUID()+"."+suffixName;

            //判断是否存在目录，如果不存在，则创建目录
            if(!new File(path).exists()){
                new File(path).mkdirs();
            }

            File dest = new java.io.File(path,genName);
            file.transferTo(dest);
            return PropertyUtil.getConfigureProperties("UPLOAD_VIRTUAL_PATH")+"/"+extendUrl+"/"+genName;
        } catch (Exception e) {
            e.printStackTrace();
           return null;
        }

    }

    @Override
    public Integer deleteById(Integer id) throws Exception {
        return mobileAppVersionDaoMapper.deleteById(id);
    }

    @Override
    public void update(MobileAppVersionBean record) throws Exception {
        mobileAppVersionDaoMapper.update(record);
    }

    @Override
    public Integer insert(MobileAppVersionBean record) throws Exception {
        return mobileAppVersionDaoMapper.insert(record);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer publicVersion(Integer id) throws Exception {
        mobileAppVersionDaoMapper.unPublicVersion(id);
        return mobileAppVersionDaoMapper.publicVersion(id);
    }

    @Override
    public Integer checkCode(MobileAppVersionBean bean) throws Exception {
        return mobileAppVersionDaoMapper.checkCode(bean);
    }

    @Override
    public Integer selectCount(MobileAppVersionBean bean) throws Exception {
        return mobileAppVersionDaoMapper.selectCount(bean);
    }

    @Override
    public List<MobileAppVersionBean> selectList(MobileAppVersionBean bean) throws Exception {
        return mobileAppVersionDaoMapper.selectList(bean);
    }

    /**
     * 获取最新版本
     * @return
     * @throws Exception
     */
    @Override
    public MobileAppVersionBean getNewVersion() throws Exception{
        return  mobileAppVersionDaoMapper.getNewVersion();
    }
}
