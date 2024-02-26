window.onload = function() {
    $(document).ready(function() {
        $('#summernote').summernote( {
            height: 500, // 에디터 높이
            lang: "ko-KR",
            placeholder: "내용을 입력해주세요",

            callbacks: {
                onImageUpload: function(files) {
                    uploadSummernoteImageFile(files[0], this);
                }
            }
        });
    });
}

function uploadSummernoteImageFile(file, editor) {
    data = new FormData();
    data.append("file", file);
    $.ajax( {
        data : data,
        type : "POST",
        url : "/uploadSummernoteImageFile",
        contentType : false,
        processData: false,
        success: function(data) {
            // 업로드된 파일의 url이 있어야 함
            alert(data.url);
            $(editor).summernote('insertImage', data.url);
        }
    });
}
