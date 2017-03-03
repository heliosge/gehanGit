var ScormRTE = {};
var cmicore = {};


ScormRTE.prototype = {
	LMSInitialize: function(param) {
		scormFlag = false;
		return "true";
	},

	LMSFinish: function(param) {
		if(cmicore.core_lesson_status != undefined  && cmicore.core_lesson_status != 'incompleted'){
			for( var j=0;j<studyed.length;j++){
				if(studyed[j] == sco){
					studyed.splice(j,1);
					break;
				};
			}
			studyed.push(sco);
		}
		return "true";
	},

	LMSSetValue: function(element, value) {
		switch(element)
        {
            case "cmi.core.student_id":
                //buttonform.studentID.value = value;
                break;
            case "cmi.core.student_name":
                //buttonform.studentName.value = value;
                break;
            case "cmi.core.lesson_location":
            	cmicore.core_lesson_location = value;
                break;
            case "cmi.core.credit":
            	cmicore.core_credit = value
                break;
            case "cmi.core.lesson_status":
                if(cmicore.core_lesson_status != "completed"){
                	cmicore.core_lesson_status = value;
                }
                break;
            case "cmi.core.entry":
                cmicore.core_entry = value;
                break;
            case "cmi.core.score":
                //buttonform.score.value = value;
                break;
            case "cmi.core.score.raw":
                cmicore.core_score_raw = value;
                break;
            case "cmi.core.total_time":
                cmicore.core_total_time = value;
                break;
            case "cmi.core.lesson_mode":
                cmicore.core_lesson_mode = value;
                break;
            case "cmi.core.exit":
                cmicore.core_exit = value;
                break;
            case "cmi.core.session_time":
                cmicore.core_session_time = value;
                break;
            case "cmi.suspend_data":
                cmicore.suspend_data = value;
                break;
            default:
                break; 
        }  
		return "true";
	},

	LMSGetValue: function(element) {
		switch(element)
        {
            case "cmi.core.student_id":
            	return cmicore.core_student_id;
                break;
            case "cmi.core.student_name":
            	return cmicore.core_student_name;
                break;
            case "cmi.core.lesson_location":
            	return cmicore.core_lesson_location;
                //buttonform.scoLocation.value = value;
                break;
            case "cmi.core.credit":
            	return cmicore.core_credit;
                break;
            case "cmi.core.lesson_status":
                return cmicore.core_lesson_status;
                break;
            case "cmi.core.entry":
                return cmicore.core_entry;
                break;
            case "cmi.core.score":
                //buttonform.score.value = value;
                break;
            case "cmi.core.score.raw":
                return cmicore.core_score_raw;
                break;
            case "cmi.core.total_time":
                return cmicore.core_total_time;
                break;
            case "cmi.core.lesson_mode":
                return cmicore.core_lesson_mode;
                break;
            case "cmi.core.exit":
                return cmicore.core_exit;
                break;
            case "cmi.core.session_time":
                return cmicore.core_session_time;
                break;
            case "cmi.suspend_data":
                return cmicore.suspend_data;
                break;
            default:
                break; 
        }  
		return "true";
	},

	LMSCommit: function(param) {
		return "true";
	},

	LMSGetLastError: function() {
		//sendData('getlasterr', "")
		return "";
	},

	LMSGetErrorString: function(errcode) {
		//sendData('geterrstring', errcode)
		return "";
	},

	LMSGetDiagnostic: function(errcode) {
		//sendData('getdiagnostic', errcode)
		return "";
	}
};

function init_API () {
	window['API'] = ScormRTE.prototype;
}