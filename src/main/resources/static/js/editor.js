const editor = ClassicEditor
    .create(document.querySelector('#editor'))
    .then(editor => {
        window.editor = editor;
    })
    .catch(error => {
        console.error("에디터 생성 중 에러 발생",error);
    });
