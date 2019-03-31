var imgPreview = new Vue({
    el: '#main',
    data: {
        images: []
    },

    methods: {
        multi: function () {
            let files = document.getElementById("multi-input").files
            this.images = []
            for (var i = 0; i < files.length; i++) {
                imgPreview.images.push({url: URL.createObjectURL(files[i])})
            }
        },

        postMulti: function () {
            /*      let files = $event.target.files || $event.dataTransfer.files;*/
            let files = document.getElementById("multi-input").files
            var attachment = new FormData

            for (var i = 0; i < files.length; i++) {
                attachment.append('file', files[i])
            }
            axios.post('/upload', attachment, {headers: {"Content-type": "multipart/form-data"}});
        }
    }
});