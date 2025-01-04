const bar = document.getElementById('bar');
const nav = document.getElementById('navbar');
const close = document.getElementById('close');

if (bar){
    bar.addEventListener('click', () => {
        nav.classList.add('active');
    });
}
if (close) {
    close.addEventListener('click', () => {
        nav.classList.remove('active');
    });
}
// Lấy tham chiếu đến nút login
document.getElementById("log in").onclick = function() {
    // Chuyển hướng đến trang login
    window.location.href = "D:\test\Studis web\Login\Index.html";
};
