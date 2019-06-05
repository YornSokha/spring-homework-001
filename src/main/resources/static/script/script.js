function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah').attr({'src': e.target.result}).css('display', 'block')
        }
        reader.readAsDataURL(input.files[0]);
    }
}
$("#file").change(function () {
    readURL(this);
});

$('.delete').on('click', (e)=>{
    Swal.fire({
        title: 'Are you sure to delete?',
        text: "This cannot be undone!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.value) {
            // Swal.fire({
            //         position: 'top-end',
            //         type: 'success',
            //         title: 'Article has been deleted',
            //         showConfirmButton: false,
            //         timer: 1000
            //     }
            // );
            $.notify("Article deleted", "success");
            setInterval(() =>{$(e.target).closest('form').submit();}, 1200);
        }
    })
});