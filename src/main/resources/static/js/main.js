var imgPreview = new Vue({
    el: '#main',
    data: {
        multiImages: [],
        base64Images: [],
        base64Objects: [],
    },

    methods: {
        previewMulti: function () {
            let files = document.getElementById("multi-input").files
            this.multiImages = [];
            for (var i = 0; i < files.length; i++) {
                this.multiImages.push({url: URL.createObjectURL(files[i])});
            }
        },

        postMulti: function () {
            let files = document.getElementById("multi-input").files
            var attachment = new FormData

            for (var i = 0; i < files.length; i++) {
                attachment.append('file', files[i])
            }
            axios.post('/upload', attachment, {headers: {"Content-type": "multipart/form-data"}});
        },

        previewBase64: function () {
            let files = document.getElementById("base64-input").files
            this.base64Images = [];
            this.base64Objects = [];
            for (var i = 0; i < files.length; i++) {
                this.base64Images.push({url: URL.createObjectURL(files[i]), name: files[i].name});
            }
            this.getBase64();
        },

        getBase64Inside: function (file) {
            var _this = this;
            var canvas = document.createElement('CANVAS');
            var img = document.createElement('img');
            var fileName = file.name;
            img.onload = function (e) {
                canvas.height = img.height;
                canvas.width = img.width;
                let encoded = canvas.toDataURL('image/png');
                console.log(encoded);
                console.log(img.name);
                _this.base64Objects.push({encoded: encoded, name: fileName});
                canvas = null;
                this.synhroBase64 = "Finish"
            };
            img.src = file.url;

        },

        getBase64: function () {
            for (var i = 0; i < this.base64Images.length; i++) {
                this.getBase64Inside(this.base64Images[i])
            }
        },

        postBase64: function () {
            let files = document.getElementById("base64-input").files
            axios.post('/upload', this.base64Objects, {headers: {'Content-Type': 'application/json'}});
        },


    }
});