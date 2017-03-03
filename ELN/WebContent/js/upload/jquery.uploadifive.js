/*
UploadiFive 1.2.2
Copyright (c) 2012 Reactive Apps, Ronnie Garcia
Released under the UploadiFive Standard License <http://www.uploadify.com/uploadifive-standard-license>
*/
; (function ($) {

    var mimeTypes = {
        ".323": "text/h323", ".3gp": "video/3gpp", ".aab": "application/x-authoware-bin", ".aam": "application/x-authoware-map", ".aas": "application/x-authoware-seg", ".acx": "application/internet-property-stream", ".ai": "application/postscript", ".aif": "audio/x-aiff", ".aifc": "audio/x-aiff", ".aiff": "audio/x-aiff", ".als": "audio/X-Alpha5", ".amc": "application/x-mpeg", ".ani": "application/octet-stream", ".apk": "application/vnd.android.package-archive", ".asc": "text/plain", ".asd": "application/astound", ".asf": "video/x-ms-asf", ".asn": "application/astound", ".asp": "application/x-asap", ".asr": "video/x-ms-asf", ".asx": "video/x-ms-asf", ".au": "audio/basic", ".avb": "application/octet-stream", ".avi": "video/x-msvideo", ".awb": "audio/amr-wb", ".axs": "application/olescript", ".bas": "text/plain", ".bcpio": "application/x-bcpio", ".bin ": "application/octet-stream", ".bld": "application/bld", ".bld2": "application/bld2", ".bmp": "image/bmp", ".bpk": "application/octet-stream", ".bz2": "application/x-bzip2", ".c": "text/plain", ".cal": "image/x-cals", ".cat": "application/vnd.ms-pkiseccat", ".ccn": "application/x-cnc", ".cco": "application/x-cocoa", ".cdf": "application/x-cdf", ".cer": "application/x-x509-ca-cert", ".cgi": "magnus-internal/cgi", ".chat": "application/x-chat", ".class": "application/octet-stream", ".clp": "application/x-msclip", ".cmx": "image/x-cmx", ".co": "application/x-cult3d-object", ".cod": "image/cis-cod", ".conf": "text/plain", ".cpio": "application/x-cpio", ".cpp": "text/plain", ".cpt": "application/mac-compactpro", ".crd": "application/x-mscardfile", ".crl": "application/pkix-crl", ".crt": "application/x-x509-ca-cert", ".csh": "application/x-csh", ".csm": "chemical/x-csml", ".csml": "chemical/x-csml", ".css": "text/css", ".cur": "application/octet-stream", ".dcm": "x-lml/x-evm", ".dcr": "application/x-director", ".dcx": "image/x-dcx", ".der": "application/x-x509-ca-cert", ".dhtml": "text/html", ".dir": "application/x-director", ".dll": "application/x-msdownload", ".dmg": "application/octet-stream", ".dms": "application/octet-stream", ".doc": "application/msword", ".docx": "application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".dot": "application/msword", ".dvi": "application/x-dvi", ".dwf": "drawing/x-dwf", ".dwg": "application/x-autocad", ".dxf": "application/x-autocad", ".dxr": "application/x-director", ".ebk": "application/x-expandedbook", ".emb": "chemical/x-embl-dl-nucleotide", ".embl": "chemical/x-embl-dl-nucleotide", ".eps": "application/postscript", ".epub": "application/epub+zip", ".eri": "image/x-eri", ".es": "audio/echospeech", ".esl": "audio/echospeech", ".etc": "application/x-earthtime", ".etx": "text/x-setext", ".evm": "x-lml/x-evm", ".evy": "application/envoy", ".exe": "application/octet-stream", ".fh4": "image/x-freehand", ".fh5": "image/x-freehand", ".fhc": "image/x-freehand", ".fif": "application/fractals", ".flr": "x-world/x-vrml", ".flv": "flv-application/octet-stream", ".fm": "application/x-maker", ".fpx": "image/x-fpx", ".fvi": "video/isivideo", ".gau": "chemical/x-gaussian-input", ".gca": "application/x-gca-compressed", ".gdb": "x-lml/x-gdb", ".gif": "image/gif", ".gps": "application/x-gps", ".gtar": "application/x-gtar", ".gz": "application/x-gzip", ".h": "text/plain", ".hdf": "application/x-hdf", ".hdm": "text/x-hdml", ".hdml": "text/x-hdml", ".hlp": "application/winhlp", ".hqx": "application/mac-binhex40", ".hta": "application/hta", ".htc": "text/x-component", ".htm": "text/html", ".html": "text/html", ".hts": "text/html", ".htt": "text/webviewhtml", ".ice": "x-conference/x-cooltalk", ".ico": "image/x-icon", ".ief": "image/ief", ".ifm": "image/gif", ".ifs": "image/ifs", ".iii": "application/x-iphone", ".imy": "audio/melody", ".ins": "application/x-internet-signup", ".ips": "application/x-ipscript", ".ipx": "application/x-ipix", ".isp": "application/x-internet-signup", ".it": "audio/x-mod", ".itz": "audio/x-mod", ".ivr": "i-world/i-vrml", ".j2k": "image/j2k", ".jad": "text/vnd.sun.j2me.app-descriptor", ".jam": "application/x-jam", ".jar": "application/java-archive", ".java": "text/plain", ".jfif": "image/pipeg", ".jnlp": "application/x-java-jnlp-file", ".jpe": "image/jpeg", ".jpeg": "image/jpeg", ".jpg": "image/jpeg", ".jpz": "image/jpeg", ".js": "application/x-javascript", ".jwc": "application/jwc", ".kjx": "application/x-kjx", ".lak": "x-lml/x-lak", ".latex": "application/x-latex", ".lcc": "application/fastman", ".lcl": "application/x-digitalloca", ".lcr": "application/x-digitalloca", ".lgh": "application/lgh", ".lha": "application/octet-stream", ".lml": "x-lml/x-lml", ".lmlpack": "x-lml/x-lmlpack", ".log": "text/plain", ".lsf": "video/x-la-asf", ".lsx": "video/x-la-asf", ".lzh": "application/octet-stream", ".m13": "application/x-msmediaview", ".m14": "application/x-msmediaview", ".m15": "audio/x-mod", ".m3u": "audio/x-mpegurl", ".m3url": "audio/x-mpegurl", ".m4a": "audio/mp4a-latm", ".m4b": "audio/mp4a-latm", ".m4p": "audio/mp4a-latm", ".m4u": "video/vnd.mpegurl", ".m4v": "video/x-m4v", ".ma1": "audio/ma1", ".ma2": "audio/ma2", ".ma3": "audio/ma3", ".ma5": "audio/ma5", ".man": "application/x-troff-man", ".map": "magnus-internal/imagemap", ".mbd": "application/mbedlet", ".mct": "application/x-mascot", ".mdb": "application/x-msaccess", ".mdz": "audio/x-mod", ".me": "application/x-troff-me", ".mel": "text/x-vmel", ".mht": "message/rfc822", ".mhtml": "message/rfc822", ".mi": "application/x-mif", ".mid": "audio/mid", ".midi": "audio/midi", ".mif": "application/x-mif", ".mil": "image/x-cals", ".mio": "audio/x-mio", ".mmf": "application/x-skt-lbs", ".mng": "video/x-mng", ".mny": "application/x-msmoney", ".moc": "application/x-mocha", ".mocha": "application/x-mocha", ".mod": "audio/x-mod", ".mof": "application/x-yumekara", ".mol": "chemical/x-mdl-molfile", ".mop": "chemical/x-mopac-input", ".mov": "video/quicktime", ".movie": "video/x-sgi-movie", ".mp2": "video/mpeg", ".mp3": "audio/mpeg", ".mp4": "video/mp4", ".mpa": "video/mpeg", ".mpc": "application/vnd.mpohun.certificate", ".mpe": "video/mpeg", ".mpeg": "video/mpeg", ".mpg": "video/mpeg", ".mpg4": "video/mp4", ".mpga": "audio/mpeg", ".mpn": "application/vnd.mophun.application", ".mpp": "application/vnd.ms-project", ".mps": "application/x-mapserver", ".mpv2": "video/mpeg", ".mrl": "text/x-mrml", ".mrm": "application/x-mrm", ".ms": "application/x-troff-ms", ".msg": "application/vnd.ms-outlook", ".mts": "application/metastream", ".mtx": "application/metastream", ".mtz": "application/metastream", ".mvb": "application/x-msmediaview", ".mzv": "application/metastream", ".nar": "application/zip", ".nbmp": "image/nbmp", ".nc": "application/x-netcdf", ".ndb": "x-lml/x-ndb", ".ndwn": "application/ndwn", ".nif": "application/x-nif", ".nmz": "application/x-scream", ".nokia-op-logo": "image/vnd.nok-oplogo-color", ".npx": "application/x-netfpx", ".nsnd": "audio/nsnd", ".nva": "application/x-neva1", ".nws": "message/rfc822", ".oda": "application/oda", ".ogg": "audio/ogg", ".oom": "application/x-AtlasMate-Plugin", ".p10": "application/pkcs10", ".p12": "application/x-pkcs12", ".p7b": "application/x-pkcs7-certificates", ".p7c": "application/x-pkcs7-mime", ".p7m": "application/x-pkcs7-mime", ".p7r": "application/x-pkcs7-certreqresp", ".p7s": "application/x-pkcs7-signature", ".pac": "audio/x-pac", ".pae": "audio/x-epac", ".pan": "application/x-pan", ".pbm": "image/x-portable-bitmap", ".pcx": "image/x-pcx", ".pda": "image/x-pda", ".pdb": "chemical/x-pdb", ".pdf": "application/pdf", ".pfr": "application/font-tdpfr", ".pfx": "application/x-pkcs12", ".pgm": "image/x-portable-graymap", ".pict": "image/x-pict", ".pko": "application/ynd.ms-pkipko", ".pm": "application/x-perl", ".pma": "application/x-perfmon", ".pmc": "application/x-perfmon", ".pmd": "application/x-pmd", ".pml": "application/x-perfmon", ".pmr": "application/x-perfmon", ".pmw": "application/x-perfmon", ".png": "image/png", ".pnm": "image/x-portable-anymap", ".pnz": "image/png", ".pot,": "application/vnd.ms-powerpoint", ".ppm": "image/x-portable-pixmap", ".pps": "application/vnd.ms-powerpoint", ".ppt": "application/vnd.ms-powerpoint", ".pptx": "application/vnd.openxmlformats-officedocument.presentationml.presentation", ".pqf": "application/x-cprplayer", ".pqi": "application/cprplayer", ".prc": "application/x-prc", ".prf": "application/pics-rules", ".prop": "text/plain", ".proxy": "application/x-ns-proxy-autoconfig", ".ps": "application/postscript", ".ptlk": "application/listenup", ".pub": "application/x-mspublisher", ".pvx": "video/x-pv-pvx", ".qcp": "audio/vnd.qcelp", ".qt": "video/quicktime", ".qti": "image/x-quicktime", ".qtif": "image/x-quicktime", ".r3t": "text/vnd.rn-realtext3d", ".ra": "audio/x-pn-realaudio", ".ram": "audio/x-pn-realaudio", ".rar": "application/octet-stream", ".ras": "image/x-cmu-raster", ".rc": "text/plain", ".rdf": "application/rdf+xml", ".rf": "image/vnd.rn-realflash", ".rgb": "image/x-rgb", ".rlf": "application/x-richlink", ".rm": "audio/x-pn-realaudio", ".rmf": "audio/x-rmf", ".rmi": "audio/mid", ".rmm": "audio/x-pn-realaudio", ".rmvb": "audio/x-pn-realaudio", ".rnx": "application/vnd.rn-realplayer", ".roff": "application/x-troff", ".rp": "image/vnd.rn-realpix", ".rpm": "audio/x-pn-realaudio-plugin", ".rt": "text/vnd.rn-realtext", ".rte": "x-lml/x-gps", ".rtf": "application/rtf", ".rtg": "application/metastream", ".rtx": "text/richtext", ".rv": "video/vnd.rn-realvideo", ".rwc": "application/x-rogerwilco", ".s3m": "audio/x-mod", ".s3z": "audio/x-mod", ".sca": "application/x-supercard", ".scd": "application/x-msschedule", ".sct": "text/scriptlet", ".sdf": "application/e-score", ".sea": "application/x-stuffit", ".setpay": "application/set-payment-initiation", ".setreg": "application/set-registration-initiation", ".sgm": "text/x-sgml", ".sgml": "text/x-sgml", ".sh": "application/x-sh", ".shar": "application/x-shar", ".shtml": "magnus-internal/parsed-html", ".shw": "application/presentations", ".si6": "image/si6", ".si7": "image/vnd.stiwap.sis", ".si9": "image/vnd.lgtwap.sis", ".sis": "application/vnd.symbian.install", ".sit": "application/x-stuffit", ".skd": "application/x-Koan", ".skm": "application/x-Koan", ".skp": "application/x-Koan", ".skt": "application/x-Koan", ".slc": "application/x-salsa", ".smd": "audio/x-smd", ".smi": "application/smil", ".smil": "application/smil", ".smp": "application/studiom", ".smz": "audio/x-smd", ".snd": "audio/basic", ".spc": "application/x-pkcs7-certificates", ".spl": "application/futuresplash", ".spr": "application/x-sprite", ".sprite": "application/x-sprite", ".sdp": "application/sdp", ".spt": "application/x-spt", ".src": "application/x-wais-source", ".sst": "application/vnd.ms-pkicertstore", ".stk": "application/hyperstudio", ".stl": "application/vnd.ms-pkistl", ".stm": "text/html", ".svg": "image/svg+xml", ".sv4cpio": "application/x-sv4cpio", ".sv4crc": "application/x-sv4crc", ".svf": "image/vnd", ".svh": "image/svh", ".svr": "x-world/x-svr", ".swf": "application/x-shockwave-flash", ".swfl": "application/x-shockwave-flash", ".t": "application/x-troff", ".tad": "application/octet-stream", ".talk": "text/x-speech", ".tar": "application/x-tar", ".taz": "application/x-tar", ".tbp": "application/x-timbuktu", ".tbt": "application/x-timbuktu", ".tcl": "application/x-tcl", ".tex": "application/x-tex", ".texi": "application/x-texinfo", ".texinfo": "application/x-texinfo", ".tgz": "application/x-compressed", ".thm": "application/vnd.eri.thm", ".tif": "image/tiff", ".tiff": "image/tiff", ".tki": "application/x-tkined", ".tkined": "application/x-tkined", ".toc": "application/toc", ".toy": "image/toy", ".tr": "application/x-troff", ".trk": "x-lml/x-gps", ".trm": "application/x-msterminal", ".tsi": "audio/tsplayer", ".tsp": "application/dsptype", ".tsv": "text/tab-separated-values", ".ttf": "application/octet-stream", ".ttz": "application/t-time", ".txt": "text/plain", ".uls": "text/iuls", ".ult": "audio/x-mod", ".ustar": "application/x-ustar", ".uu": "application/x-uuencode", ".uue": "application/x-uuencode", ".vcd": "application/x-cdlink", ".vcf": "text/x-vcard", ".vdo": "video/vdo", ".vib": "audio/vib", ".viv": "video/vivo", ".vivo": "video/vivo", ".vmd": "application/vocaltec-media-desc", ".vmf": "application/vocaltec-media-file", ".vmi": "application/x-dreamcast-vms-info", ".vms": "application/x-dreamcast-vms", ".vox": "audio/voxware", ".vqe": "audio/x-twinvq-plugin", ".vqf": "audio/x-twinvq", ".vql": "audio/x-twinvq", ".vre": "x-world/x-vream", ".vrml": "x-world/x-vrml", ".vrt": "x-world/x-vrt", ".vrw": "x-world/x-vream", ".vts": "workbook/formulaone", ".wav": "audio/x-wav", ".wax": "audio/x-ms-wax", ".wbmp": "image/vnd.wap.wbmp", ".wcm": "application/vnd.ms-works", ".wdb": "application/vnd.ms-works", ".web": "application/vnd.xara", ".wi": "image/wavelet", ".wis": "application/x-InstallShield", ".wks": "application/vnd.ms-works", ".wm": "video/x-ms-wm", ".wma": "audio/x-ms-wma", ".wmd": "application/x-ms-wmd", ".wmf": "application/x-msmetafile", ".wml": "text/vnd.wap.wml", ".wmlc": "application/vnd.wap.wmlc", ".wmls": "text/vnd.wap.wmlscript", ".wmlsc": "application/vnd.wap.wmlscriptc", ".wmlscript": "text/vnd.wap.wmlscript", ".wmv": "audio/x-ms-wmv", ".wmx": "video/x-ms-wmx", ".wmz": "application/x-ms-wmz", ".wpng": "image/x-up-wpng", ".wps": "application/vnd.ms-works", ".wpt": "x-lml/x-gps", ".wri": "application/x-mswrite", ".wrl": "x-world/x-vrml", ".wrz": "x-world/x-vrml", ".ws": "text/vnd.wap.wmlscript", ".wsc": "application/vnd.wap.wmlscriptc", ".wv": "video/wavelet", ".wvx": "video/x-ms-wvx", ".wxl": "application/x-wxl", ".x-gzip": "application/x-gzip", ".xaf": "x-world/x-vrml", ".xar": "application/vnd.xara", ".xbm": "image/x-xbitmap", ".xdm": "application/x-xdma", ".xdma": "application/x-xdma", ".xdw": "application/vnd.fujixerox.docuworks", ".xht": "application/xhtml+xml", ".xhtm": "application/xhtml+xml", ".xhtml": "application/xhtml+xml", ".xla": "application/vnd.ms-excel", ".xlc": "application/vnd.ms-excel", ".xll": "application/x-excel", ".xlm": "application/vnd.ms-excel", ".xls": "application/vnd.ms-excel", ".xlsx": "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlt": "application/vnd.ms-excel", ".xlw": "application/vnd.ms-excel", ".xm": "audio/x-mod", ".xml": "text/plain", ".xmz": "audio/x-mod", ".xof": "x-world/x-vrml", ".xpi": "application/x-xpinstall", ".xpm": "image/x-xpixmap", ".xsit": "text/xml", ".xsl": "text/xml", ".xul": "text/xul", ".xwd": "image/x-xwindowdump", ".xyz": "chemical/x-pdb", ".yz1": "application/x-yz1", ".z": "application/x-compress", ".zac": "application/x-zaurus-zac", ".zip": "application/zip", ".json": "application/json"
    };
    
    var methods = {
        init: function (options) {

            return this.each(function () {

                // Create a reference to the jQuery DOM object
                var $this = $(this);
                $this.data('uploadifive', {
                    inputs: {}, // The object that contains all the file inputs
                    inputCount: 0,  // The total number of file inputs created
                    fileID: 0,
                    queue: {
                        count: 0, // Total number of files in the queue
                        selected: 0, // Number of files selected in the last select operation
                        replaced: 0, // Number of files replaced in the last select operation
                        errors: 0, // Number of files that returned an error in the last select operation
                        queued: 0, // Number of files added to the queue in the last select operation
                        cancelled: 0  // Total number of files that have been cancelled or removed from the queue
                    },
                    uploads: {
                        current: 0, // Number of files currently being uploaded
                        attempts: 0, // Number of file uploads attempted in the last upload operation
                        successful: 0, // Number of files successfully uploaded in the last upload operation
                        errors: 0, // Number of files returning errors in the last upload operation
                        count: 0  // Total number of files uploaded successfully
                    }
                });
                var $data = $this.data('uploadifive');

                // Set the default options
                var settings = $data.settings = $.extend({
                    'auto': true,               // Automatically upload a file when it's added to the queue
                    'buttonClass': false,              // A class to add to the UploadiFive button
                    'buttonText': 'Select Files',     // The text that appears on the UploadiFive button
                    'checkScript': false,              // Path to the script that checks for existing file names 
                    'dnd': true,               // Allow drag and drop into the queue
                    'dropTarget': false,              // Selector for the drop target
                    'fileObjName': 'Filedata',         // The name of the file object to use in your server-side script
                    'fileSizeLimit': 0,                  // Maximum allowed size of files to upload
                    'fileTypeExts': false,              // Type of files allowed (image, etc), separate with a pipe character |
                    'formData': {},                 // Additional data to send to the upload script
                    'height': 30,                 // The height of the button
                    'itemTemplate': false,              // The HTML markup for the item in the queue
                    'method': 'post',             // The method to use when submitting the upload
                    'multi': true,               // Set to true to allow multiple file selections
                    'overrideEvents': [],                 // An array of events to override
                    'queueID': false,              // The ID of the file queue
                    'queueSizeLimit': 0,                  // The maximum number of files that can be in the queue
                    'removeCompleted': false,              // Set to true to remove files that have completed uploading
                    'simUploadLimit': 0,                  // The maximum number of files to upload at once
                    'truncateLength': 0,                  // The length to truncate the file names to
                    'uploadLimit': 0,                  // The maximum number of files you can upload
                    'uploader': 'uploadifive.php',  // The path to the upload script
                    'width': 100                 // The width of the button

                    /*
                    // Events
                    'onAddQueueItem'   : function(file) {},                        // Triggered for each file that is added to the queue
                    'onCancel'         : function(file) {},                        // Triggered when a file is cancelled or removed from the queue
                    'onCheck'          : function(file, exists) {},                // Triggered when the server is checked for an existing file
                    'onClearQueue'     : function(queue) {},                       // Triggered during the clearQueue function
                    'onDestroy'        : function() {}                             // Triggered during the destroy function
                    'onDrop'           : function(files, numberOfFilesDropped) {}, // Triggered when files are dropped into the file queue
                    'onError'          : function(file, fileTypeExts, data) {},        // Triggered when an error occurs
                    'onFallback'       : function() {},                            // Triggered if the HTML5 File API is not supported by the browser
                    'onInit'           : function() {},                            // Triggered when UploadiFive if initialized
                    'onUploadSuccess'  : function() {},                            // Triggered once when an upload queue is done
                    'onProgress'       : function(file, event) {},                 // Triggered during each progress update of an upload
                    'onSelect'         : function(file) {},                            // Triggered once when files are selected from a dialog box
                    'onUpload'         : function(file) {},                        // Triggered when an upload queue is started
                    'onUploadComplete' : function(file, data) {},                  // Triggered when a file is successfully uploaded
                    'onUploadFile'     : function(file) {},                        // Triggered for each file being uploaded
                    */
                }, options);

                // Calculate the file size limit
                if (isNaN(settings.fileSizeLimit)) {
                    var fileSizeLimitBytes = parseInt(settings.fileSizeLimit) * 1.024;
                    if (settings.fileSizeLimit.indexOf('KB') > -1) {
                        settings.fileSizeLimit = fileSizeLimitBytes * 1000;
                    } else if (settings.fileSizeLimit.indexOf('MB') > -1) {
                        settings.fileSizeLimit = fileSizeLimitBytes * 1000000;
                    } else if (settings.fileSizeLimit.indexOf('GB') > -1) {
                        settings.fileSizeLimit = fileSizeLimitBytes * 1000000000;
                    }
                } else {
                    settings.fileSizeLimit = settings.fileSizeLimit * 1024;
                }

                // Create a template for a file input
                $data.inputTemplate = $('<input type="file">')
                    .css({
                        'font-size': settings.height + 'px',
                        'opacity': 0,
                        'position': 'absolute',
                        'right': '-3px',
                        'top': '-3px',
                        'z-index': 999
                    });
                var getFileExt = function (filename) {
                    var lastIdx = filename.lastIndexOf('.');
                    if (lastIdx < 0) return "";
                    var extname = filename.substr(lastIdx);
                    return extname;
                };
                // Create a new input
                $data.createInput = function () {

                    // Create a clone of the file input
                    var input = $data.inputTemplate.clone();
                    // Create a unique name for the input item
                    var inputName = input.name = 'input' + $data.inputCount++;
                    // Set the multiple attribute
                    if (settings.multi) {
                        input.attr('multiple', true);
                    }
                    // Set the accept attribute on the input
                    if (settings.fileTypeExts) {
                        var acceptType = "";
                        if (settings.fileTypeExts.indexOf(";") > 0) {
                            var arr=settings.fileTypeExts.split(';');
                            $.each(arr,function (idx, item) {
                                var mime = mimeTypes[item.trim().substr(1)];
                                if (mime != undefined) {
                                    if (acceptType != "") acceptType += ",";
                                    if (acceptType.indexOf(mime) < 0)
                                        acceptType += mime;
                                }
                            });
                            //acceptType = settings.fileTypeExts.replaceAll(';', '|');
                        } else {
                            //acceptType = settings.fileTypeExts;
                            var tmp = mimeTypes[settings.fileTypeExts.trim().substr(1)];
                            if (tmp != undefined) {
                                acceptType = tmp;
                            }
                        }
                        input.attr('accept', acceptType);
                    }
                    // Set the onchange event for the input
                    input.bind('change', function () {
                        $data.queue.selected = 0;
                        $data.queue.replaced = 0;
                        $data.queue.errors = 0;
                        $data.queue.queued = 0;
                        // Add a queue item to the queue for each file
                        var limit = this.files.length;
                        $data.queue.selected = limit;
                        if (($data.queue.count + limit) > settings.queueSizeLimit && settings.queueSizeLimit !== 0) {
                            if ($.inArray('onError', settings.overrideEvents) < 0) {
                                alert('The maximum number of queue items has been reached (' + settings.queueSizeLimit + ').  Please select fewer files.');
                            }
                            // Trigger the error event
                            if (typeof settings.onError === 'function') {
                                settings.onError.call($this, 'QUEUE_LIMIT_EXCEEDED');
                            }
                        } else {
                            for (var n = 0; n < limit; n++) {
                                //window.file = this.files[n];
                                //$data.addQueueItem(file);

                                window.file = this.files[n];
                                if(settings.fileTypeExts){
                                    var exts = file.name.substring(file.name.lastIndexOf("."))+";";
                                    if ((settings.fileTypeExts+";").indexOf(exts) > -1) {
                                        $data.addQueueItem(file);
                                        if (typeof settings.onSelect === 'function') {
                                            settings.onSelect.call($this, file);
                                        }
                                    }else{
                                        alert(file.name+", the file format is not valid!");
                                    }
                                }else{
                                    $data.addQueueItem(file);
                                }
                            }
                            $data.inputs[inputName] = this;
                            $data.createInput();
                        }
                        // Upload the file if auto-uploads are enabled
                        if (settings.auto) {
                            methods.upload.call($this);
                        }
                        //// Trigger the select event
                        //if (typeof settings.onSelect === 'function') {
                        //    settings.onSelect.call($this, file);
                        //}
                    });
                    // Hide the existing current item and add the new one
                    if ($data.currentInput) {
                        $data.currentInput.hide();
                    }
                    $data.button.append(input);
                    $data.currentInput = input;
                };

                // Remove an input
                $data.destroyInput = function (key) {
                    $($data.inputs[key]).remove();
                    delete $data.inputs[key];
                    $data.inputCount--;
                };

                // Drop a file into the queue
                $data.drop = function (e) {
                    $data.queue.selected = 0;
                    $data.queue.replaced = 0;
                    $data.queue.errors = 0;
                    $data.queue.queued = 0;

                    var fileData = e.dataTransfer;

                    var inputName = fileData.name = 'input' + $data.inputCount++;
                    // Add a queue item to the queue for each file
                    var limit = fileData.files.length;
                    $data.queue.selected = limit;
                    if (($data.queue.count + limit) > settings.queueSizeLimit && settings.queueSizeLimit !== 0) {
                        // Check if the queueSizeLimit was reached
                        if ($.inArray('onError', settings.overrideEvents) < 0) {
                            alert('The maximum number of queue items has been reached (' + settings.queueSizeLimit + ').  Please select fewer files.');
                        }
                        // Trigger the onError event
                        if (typeof settings.onError === 'function') {
                            settings.onError.call($this, 'QUEUE_LIMIT_EXCEEDED');
                        }
                    } else {
                        // Add a queue item for each file
                        for (var n = 0; n < limit; n++) {
                            window.file = fileData.files[n];
                            $data.addQueueItem(file);
                        }
                        // Save the data to the inputs object
                        $data.inputs[inputName] = fileData;
                    }

                    // Upload the file if auto-uploads are enabled
                    if (settings.auto) {
                        methods.upload.call($this);
                    }

                    // Trigger the onDrop event
                    if (typeof settings.onDrop === 'function') {
                        settings.onDrop.call($this, fileData.files, fileData.files.length);
                    }

                    // Stop FireFox from opening the dropped file(s)
                    e.preventDefault();
                    e.stopPropagation();
                };

                // Check if a filename exists in the queue
                $data.fileExistsInQueue = function (file) {
                    for (var key in $data.inputs) {
                        input = $data.inputs[key];
                        limit = input.files.length;
                        for (var n = 0; n < limit; n++) {
                            existingFile = input.files[n];
                            // Check if the filename matches
                            if (existingFile.name == file.name && !existingFile.complete) {
                                return true;
                            }
                        }
                    }
                    return false;
                };

                // Remove an existing file in the queue
                $data.removeExistingFile = function (file) {
                    for (var key in $data.inputs) {
                        input = $data.inputs[key];
                        limit = input.files.length;
                        for (var n = 0; n < limit; n++) {
                            existingFile = input.files[n];
                            // Check if the filename matches
                            if (existingFile.name == file.name && !existingFile.complete) {
                                $data.queue.replaced++;
                                methods.cancel.call($this, existingFile, true);
                            }
                        }
                    }
                };

                // Create the file item template
                if (settings.itemTemplate == false) {
                    $data.queueItem = $('<div class="uploadify-queue-item">\
                        <a class="close" href="#">X</a>\
                        <div><span class="filename"></span><span class="fileinfo"></span></div>\
                        <div class="progress">\
                            <div class="progress-bar"></div>\
                        </div>\
                    </div>');
                } else {
                    $data.queueItem = $(settings.itemTemplate);
                }

                // Add an item to the queue
                $data.addQueueItem = function (file) {
                    if ($.inArray('onAddQueueItem', settings.overrideEvents) < 0) {
                        // Check if the filename already exists in the queue
                        $data.removeExistingFile(file);
                        // Create a clone of the queue item template
                        file.queueItem = $data.queueItem.clone();
                        // Add an ID to the queue item
                        file.id = $data.fileID;
                        file.queueItem.attr('id', settings.id + '-file-' + $data.fileID++);
                        // Bind the close event to the close button
                        file.queueItem.find('.close').bind('click', function () {
                            methods.cancel.call($this, file);
                            return false;
                        });
                        var fileName = file.name;
                        if (fileName.length > settings.truncateLength && settings.truncateLength != 0) {
                            fileName = fileName.substring(0, settings.truncateLength) + '...';
                        }
                        file.queueItem.find('.filename').html(fileName);
                        // Add a reference to the file
                        file.queueItem.data('file', file);
                        $data.queueEl.append(file.queueItem);
                    }
                    // Trigger the addQueueItem event
                    if (typeof settings.onAddQueueItem === 'function') {
                        settings.onAddQueueItem.call($this, file);
                    }
                    // Check the filesize
                    if (file.size > settings.fileSizeLimit && settings.fileSizeLimit != 0) {
                        $data.error('FILE_SIZE_LIMIT_EXCEEDED', file);
                    } else {
                        $data.queue.queued++;
                        $data.queue.count++;
                    }
                }

                // Remove an item from the queue
                $data.removeQueueItem = function (file, instant, delay) {
                    // Set the default delay
                    if (!delay) delay = 0;
                    var fadeTime = instant ? 0 : 500;
                    if (file.queueItem) {
                        if (file.queueItem.find('.fileinfo').html() != ' - Completed') {
                            file.queueItem.find('.fileinfo').html(' - Cancelled');
                        }
                        file.queueItem.find('.progress-bar').width(0);
                        file.queueItem.delay(delay).fadeOut(fadeTime, function () {
                            $(this).remove();
                        });
                        delete file.queueItem;
                        $data.queue.count--;
                    }
                }

                // Count the number of files that need to be uploaded
                $data.filesToUpload = function () {
                    var filesToUpload = 0;
                    for (var key in $data.inputs) {
                        input = $data.inputs[key];
                        limit = input.files.length;
                        for (var n = 0; n < limit; n++) {
                            file = input.files[n];
                            if (!file.skip && !file.complete) {
                                filesToUpload++;
                            }
                        }
                    }
                    return filesToUpload;
                };

                // Check if a file exists
                $data.checkExists = function (file) {
                    if ($.inArray('onCheck', settings.overrideEvents) < 0) {
                        // This request needs to be synchronous
                        $.ajaxSetup({
                            'async': false
                        });
                        // Send the filename to the check script
                        var checkData = $.extend(settings.formData, { filename: file.name });
                        $.post(settings.checkScript, checkData, function (fileExists) {
                            file.exists = parseInt(fileExists);
                        });
                        if (file.exists) {
                            if (!confirm('A file named ' + file.name + ' already exists in the upload folder.\nWould you like to replace it?')) {
                                // If not replacing the file, cancel the upload
                                methods.cancel.call($this, file);
                                return true;
                            }
                        }
                    }
                    // Trigger the check event
                    if (typeof settings.onCheck === 'function') {
                        settings.onCheck.call($this, file, file.exists);
                    }
                    return false;
                };

                // Upload a single file
                $data.uploadFile = function (file, uploadAll) {
                    if (!file.skip && !file.complete && !file.uploading) {
                        file.uploading = true;
                        $data.uploads.current++;
                        $data.uploads.attempted++;

                        // Create a new AJAX request
                        xhr = file.xhr = new XMLHttpRequest();

                        // Start the upload
                        // Use the faster FormData if it exists
                        if (typeof FormData === 'function' || typeof FormData === 'object') {

                            // Create a new FormData object
                            var formData = new FormData();

                            // Add the form data
                            formData.append(settings.fileObjName, file);

                            // Add the rest of the formData
                            for (i in settings.formData) {
                                formData.append(i, settings.formData[i]);
                            }

                            // Open the AJAX call
                            xhr.open(settings.method, settings.uploader, true);

                            // On progress function
                            xhr.upload.addEventListener('progress', function (e) {
                                if (e.lengthComputable) {
                                    $data.progress(e, file);
                                }
                            }, false);

                            // On complete function
                            xhr.addEventListener('load', function (e) {
                                if (this.readyState == 4) {
                                    file.uploading = false;
                                    if (this.status == 200) {
                                        if (file.xhr.responseText !== 'Invalid file type.') {
                                            $data.uploadComplete(e, file, uploadAll);
                                        } else {
                                            $data.error(file.xhr.responseText, file, uploadAll);
                                        }
                                    } else if (this.status == 404) {
                                        $data.error('404_FILE_NOT_FOUND', file, uploadAll);
                                    } else if (this.status == 403) {
                                        $data.error('403_FORBIDDEN', file, uploadAll);
                                    } else {
                                        $data.error('Unknown Error', file, uploadAll);
                                    }
                                }
                            });

                            // Send the form data (multipart/form-data)
                            xhr.send(formData);

                        } else {

                            // Send as binary
                            var reader = new FileReader();
                            reader.onload = function (e) {

                                // Set some file builder variables
                                var boundary = '-------------------------' + (new Date).getTime(),
                                    dashes = '--',
                                    eol = '\r\n',
                                    binFile = '';

                                // Build an RFC2388 String 
                                binFile += dashes + boundary + eol;
                                // Generate the headers
                                binFile += 'Content-Disposition: form-data; name="' + settings.fileObjName + '"';
                                if (file.name) {
                                    binFile += '; filename="' + file.name + '"';
                                }
                                binFile += eol;
                                binFile += 'Content-Type: application/octet-stream' + eol + eol;
                                binFile += e.target.result + eol;

                                for (key in settings.formData) {
                                    binFile += dashes + boundary + eol;
                                    binFile += 'Content-Disposition: form-data; name="' + key + '"' + eol + eol;
                                    binFile += settings.formData[key] + eol;
                                }

                                binFile += dashes + boundary + dashes + eol;

                                // On progress function
                                xhr.upload.addEventListener('progress', function (e) {
                                    $data.progress(e, file);
                                }, false);

                                // On complete function
                                xhr.addEventListener('load', function (e) {
                                    file.uploading = false;
                                    var status = this.status;
                                    if (status == 404) {
                                        $data.error('404_FILE_NOT_FOUND', file, uploadAll);
                                    } else {
                                        if (file.xhr.responseText != 'Invalid file type.') {
                                            $data.uploadComplete(e, file, uploadAll);
                                        } else {
                                            $data.error(file.xhr.responseText, file, uploadAll);
                                        }
                                    }
                                }, false);

                                // Open the ajax request
                                var url = settings.uploader;
                                if (settings.method == 'get') {
                                    var params = $(settings.formData).param();
                                    url += params;
                                }
                                xhr.open(settings.method, settings.uploader, true);
                                xhr.setRequestHeader("Content-Type", "multipart/form-data; boundary=" + boundary);

                                // Trigger the uploadFile event
                                if (typeof settings.onUploadFile === 'function') {
                                    settings.onUploadFile.call($this, file);
                                }

                                // Send the file for upload
                                xhr.sendAsBinary(binFile);
                            }
                            reader.readAsBinaryString(file);

                        }
                    }
                };

                // Update a file upload's progress
                $data.progress = function (e, file) {
                    if ($.inArray('onProgress', settings.overrideEvents) < 0) {
                        if (e.lengthComputable) {
                            var percent = Math.round((e.loaded / e.total) * 100);
                        }
                        file.queueItem.find('.fileinfo').html(' - ' + percent + '%');
                        file.queueItem.find('.progress-bar').css('width', percent + '%');
                    }
                    // Trigger the progress event
                    if (typeof settings.onProgress === 'function') {
                        settings.onProgress.call($this, file, e);
                    }
                };

                // Trigger an error
                $data.error = function (errorType, file, uploadAll) {
                    if ($.inArray('onError', settings.overrideEvents) < 0) {
                        // Get the error message
                        switch (errorType) {
                            case '404_FILE_NOT_FOUND':
                                errorMsg = '404 Error';
                                break;
                            case '403_FORBIDDEN':
                                errorMsg = '403 Forbidden';
                                break;
                            case 'FORBIDDEN_FILE_TYPE':
                                errorMsg = 'Forbidden File Type';
                                break;
                            case 'FILE_SIZE_LIMIT_EXCEEDED':
                                errorMsg = 'File Too Large';
                                break;
                            default:
                                errorMsg = 'Unknown Error';
                                break;
                        }

                        // Add the error class to the queue item
                        file.queueItem.addClass('error')
                            // Output the error in the queue item
                            .find('.fileinfo').html(' - ' + errorMsg);
                        // Hide the 
                        file.queueItem.find('.progress').remove();
                    }
                    // Trigger the error event
                    if (typeof settings.onError === 'function') {
                        settings.onError.call($this, errorType, file);
                    }
                    file.skip = true;
                    if (errorType == '404_FILE_NOT_FOUND') {
                        $data.uploads.errors++;
                    } else {
                        $data.queue.errors++;
                    }
                    if (uploadAll) {
                        methods.upload.call($this, null, true);
                    }
                }

                // Trigger when a single file upload is complete
                $data.uploadComplete = function (e, file, uploadAll) {
                    if ($.inArray('onUploadComplete', settings.overrideEvents) < 0) {
                        file.queueItem.find('.progress-bar').css('width', '100%');
                        file.queueItem.find('.fileinfo').html(' - Completed');
                        file.queueItem.find('.progress').slideUp(250);
                        file.queueItem.addClass('complete');
                    }
                    file.fileExtName = getFileExt(file.name);
                    // Trigger the complete event
                    if (typeof settings.onUploadSuccess === 'function') {
                        settings.onUploadSuccess.call($this, file, file.xhr.responseText);
                    }
                    if (settings.removeCompleted) {
                        setTimeout(function () { methods.cancel.call($this, file); }, 3000);
                    }
                    file.complete = true;
                    $data.uploads.successful++;
                    $data.uploads.count++;
                    $data.uploads.current--;
                    delete file.xhr;
                    if (uploadAll) {
                        methods.upload.call($this, null, true);
                    }
                };

                // Trigger when all the files are done uploading
                $data.queueComplete = function () {
                    // Trigger the queueComplete event
                    if (typeof settings.onQueueComplete === 'function') {
                        settings.onQueueComplete.call($this, $data.uploads);
                    }
                };

                $data.cancelFile = function (file, instant) {
                    file.skip = true;
                    $data.filesCancelled++;
                    if (file.uploading) {
                        $data.uploads.current--;
                        file.uploading = false;
                        file.xhr.abort();
                        delete file.xhr;
                        methods.upload.call($this);
                    }
                    if ($.inArray('onCancel', settings.overrideEvents) < 0) {
                        $data.removeQueueItem(file, instant);
                    }

                    // Trigger the cancel event
                    if (typeof settings.onCancel === 'function') {
                        settings.onCancel.call($this, file);
                    }
                };
                // ----------------------
                // Initialize UploadiFive
                // ----------------------

                // Check if HTML5 is available
                if (window.File && window.FileList && window.Blob && (window.FileReader || window.FormData)) {
                    // Assign an ID to the object
                    settings.id = 'uploadify-' + $this.attr('id');

                    // Wrap the file input in a div with overflow set to hidden
                    $data.button = $('<div id="' + settings.id + '" class="uploadifive-button">' + settings.buttonText + '</div>');
                    if (settings.buttonClass) $data.button.addClass(settings.buttonClass);

                    // Style the button wrapper
                    $data.button.css({
                        'height': settings.height,
                        'line-height': settings.height + 'px',
                        'overflow': 'hidden',
                        'position': 'relative',
                        'text-align': 'center',
                        'width': settings.width
                    });

                    // Insert the button above the file input
                    $this.before($data.button)
                        // Add the file input to the button
                        .appendTo($data.button)
                        // Modify the styles of the file input
                        .hide();

                    // Create a new input
                    $data.createInput.call($this);

                    // Create the queue container
                    if (!settings.queueID) {
                        settings.queueID = settings.id + '-queue';
                        $data.queueEl = $('<div id="' + settings.queueID + '" class="uploadifive-queue" />');
                        $data.button.after($data.queueEl);
                    } else {
                        $data.queueEl = $('#' + settings.queueID);
                    }

                    // Add drag and drop functionality
                    if (settings.dnd) {
                        var $dropTarget = settings.dropTarget ? $(settings.dropTarget) : $data.queueEl.get(0);
                        $dropTarget.addEventListener('dragleave', function (e) {
                            // Stop FireFox from opening the dropped file(s)
                            e.preventDefault();
                            e.stopPropagation();
                        }, false);
                        $dropTarget.addEventListener('dragenter', function (e) {
                            // Stop FireFox from opening the dropped file(s)
                            e.preventDefault();
                            e.stopPropagation();
                        }, false);
                        $dropTarget.addEventListener('dragover', function (e) {
                            // Stop FireFox from opening the dropped file(s)
                            e.preventDefault();
                            e.stopPropagation();
                        }, false);
                        $dropTarget.addEventListener('drop', $data.drop, false);
                    }

                    // Send as binary workaround for Chrome
                    if (!XMLHttpRequest.prototype.sendAsBinary) {
                        XMLHttpRequest.prototype.sendAsBinary = function (datastr) {

                            function byteValue(x) {
                                return x.charCodeAt(0) & 0xff;
                            }

                            var ords = Array.prototype.map.call(datastr, byteValue);
                            var ui8a = new Uint8Array(ords);
                            this.send(ui8a.buffer);
                        }
                    }

                    // Trigger the oninit event
                    if (typeof settings.onInit === 'function') {
                        settings.onInit.call($this);
                    }

                } else {

                    // Trigger the fallback event
                    if (typeof settings.onFallback === 'function') {
                        settings.onFallback.call($this);
                    }
                    return false;

                }

            });

        },


        // Write some data to the console
        debug: function () {

            return this.each(function () {

                console.log($(this).data('uploadifive'));

            });

        },

        // Clear all the items from the queue
        clearQueue: function () {

            this.each(function () {

                var $this = $(this),
                    $data = $this.data('uploadifive'),
                    settings = $data.settings;

                for (var key in $data.inputs) {
                    input = $data.inputs[key];
                    limit = input.files.length;
                    for (i = 0; i < limit; i++) {
                        file = input.files[i];
                        methods.cancel.call($this, file);
                    }
                }
                // Trigger the onClearQueue event
                if (typeof settings.onClearQueue === 'function') {
                    settings.onClearQueue.call($this, $('#' + $data.settings.queueID));
                }

            });

        },

        // Cancel a file upload in progress or remove a file from the queue
        cancel: function (file, fast) {

            this.each(function () {
                if (!fast) fast = 500;
                var $this = $(this),
                    $data = $this.data('uploadifive'),
                    settings = $data.settings;
                if (file == "*") {
                    $('#' + settings.queueID).find('.uploadify-queue-item').not('.error, .complete').each(function () {
                        file = $(this).data('file');
                        $data.cancelFile(file);
                    });
                    return;
                }
                // If user passed a queue item ID instead of file...
                if (!isNaN(file)) {
                    fileID = 'uploadify-' + $(this).attr('id') + '-file-' + file;
                    file = $('#' + fileID).data('file');
                    if (file != undefined)
                        $data.cancelFile(file, fast);
                    return;
                }
                $data.cancelFile(file, fast);

            });

        },

        // Upload the files in the queue
        upload: function (file, keepVars) {

            this.each(function () {

                var $this = $(this),
                    $data = $this.data('uploadifive'),
                    settings = $data.settings;

                if (file && file != "*") {
                    $data.uploadFile.call($this, file);

                } else {

                    // Check if the upload limit was reached
                    if (($data.uploads.count + $data.uploads.current) < settings.uploadLimit || settings.uploadLimit == 0) {
                        if (!keepVars) {
                            $data.uploads.attempted = 0;
                            $data.uploads.successsful = 0;
                            $data.uploads.errors = 0;
                            var filesToUpload = $data.filesToUpload();
                            // Trigger the onUpload event
                            if (typeof settings.onUpload === 'function') {
                                settings.onUpload.call($this, filesToUpload);
                            }
                        }

                        // Loop through the files
                        $('#' + settings.queueID).find('.uploadify-queue-item').not('.error, .complete').each(function () {
                            _file = $(this).data('file');
                            // Check if the simUpload limit was reached
                            if (($data.uploads.current >= settings.simUploadLimit && settings.simUploadLimit !== 0) || ($data.uploads.current >= settings.uploadLimit && settings.uploadLimit !== 0) || ($data.uploads.count >= settings.uploadLimit && settings.uploadLimit !== 0)) {
                                return false;
                            }
                            if (settings.checkScript) {
                                // Let the loop know that we're already processing this file
                                _file.checking = true;
                                skipFile = $data.checkExists(_file);
                                _file.checking = false;
                                if (!skipFile) {
                                    $data.uploadFile(_file, true);
                                }
                            } else {
                                $data.uploadFile(_file, true);
                            }
                        });
                        if ($('#' + settings.queueID).find('.uploadify-queue-item').not('.error, .complete').size() == 0) {
                            $data.queueComplete();
                        }
                    } else {
                        if ($data.uploads.current == 0) {
                            if ($.inArray('onError', settings.overrideEvents) < 0) {
                                if ($data.filesToUpload() > 0 && settings.uploadLimit != 0) {
                                    alert('The maximum upload limit has been reached.');
                                }
                            }
                            // Trigger the onError event
                            if (typeof settings.onError === 'function') {
                                settings.onError.call($this, 'UPLOAD_LIMIT_EXCEEDED', $data.filesToUpload());
                            }
                        }
                    }

                }

            });

        },

        // Destroy an instance of UploadiFive
        destroy: function () {

            this.each(function () {

                var $this = $(this),
                    $data = $this.data('uploadifive'),
                    settings = $data.settings;

                // Clear the queue
                methods.clearQueue.call($this);
                // Destroy the queue if it was created
                if (!settings.queueID) $('#' + settings.queueID).remove();
                // Remove extra inputs
                $this.siblings('input').remove();
                // Show the original file input
                $this.show()
                    // Move the file input out of the button
                    .insertBefore($data.button);
                // Delete the button
                $data.button.remove();
                // Trigger the destroy event
                if (typeof settings.onDestroy === 'function') {
                    settings.onDestroy.call($this);
                }

            });

        },

        settings: function (name, value, resetObj) {
            var args = arguments;
            var retvalue = value;
            this.each(function () {
                var $this = $(this),
                    $data = $this.data('uploadifive'),
                    settings = $data.settings;
                //if (typeof (args[0]) == 'object') {
                //    for (var n in value) {
                //        setData(n, value[n]);
                //    }
                //}
                if (typeof (value) === 'string') {
                    settings[name] = value;
                } else if (args.length === 1) {
                    retvalue = settings[name];
                } else if (name == 'formData') {
                    if (!resetObj) {
                        retvalue = $.extend(settings.formData, value);
                    } else {
                        settings.formData = value;
                    }
                }
            });
            return retvalue;
        },

        queueCount: function () {
            var qcount = 0;
            this.each(function () {
                var $this = $(this),
                    $data = $this.data('uploadifive');
                qcount = $data.queue.count;
            });
            return qcount;
        }
    };

    $.fn.uploadify = function (method) {

        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('The method ' + method + ' does not exist in $.uploadify');
            return false;
        }
    };

})(jQuery);