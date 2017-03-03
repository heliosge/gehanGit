var ScormRTE = {};


ScormRTE.prototype = {
	LMSInitialize: function(param) {
		return "true";
	},

	LMSFinish: function(param) {
		return "true";
	},

	LMSSetValue: function(element, value) {
		return "true";
	},

	LMSGetValue: function(element) {
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