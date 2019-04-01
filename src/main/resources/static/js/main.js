var imagePreview = new Vue({
    el: '#main',
    data: {
        multiImages: [],
        base64Images: [],
        base64Objects: [],
    },

    methods: {
        previewMulti: function ($event) {
            let files = $event.target.files
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

        previewBase64: function ($event) {
            let files = $event.target.files
            this.base64Images = [];
            this.base64Objects = [];
            for (var i = 0; i < files.length; i++) {
                this.base64Images.push({url: URL.createObjectURL(files[i]), image: files[i]});
            }
            this.getBase64();
        },

        getBase64: function () {
            for (var i = 0; i < this.base64Images.length; i++) {
                this.getBase64Inside(this.base64Images[i])
            }
        },

        postBase64: function () {
            axios.post('/upload', this.base64Objects, {headers: {'Content-Type': 'application/json'}});
        },

		getBase64Inside: function (file) {
		  var _this = this;
		  var reader = new FileReader();
		  reader.onloadend = function() {
			_this.base64Objects.push({encoded: reader.result, name: file.image.name});
		  }
		  reader.readAsDataURL(file.image);
		}

    }
});
