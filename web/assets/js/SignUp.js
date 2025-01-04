document.getElementById('signupForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Ngăn chặn hành vi mặc định của form

    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const errorMessage = document.getElementById('errorMessage');
    const successPopup = document.getElementById('successPopup');

    // Kiểm tra nếu mật khẩu không khớp
    if (password !== confirmPassword) {
        errorMessage.style.display = 'block'; // Hiển thị thông báo lỗi
        return; // Dừng việc gửi form
    }

    // Nếu mật khẩu khớp
    errorMessage.style.display = 'none'; // Ẩn thông báo lỗi

    // Hiển thị popup thành công
    successPopup.classList.add('show');

    // Tự động đóng popup và chuyển hướng sau 4 giây
    setTimeout(() => {
        successPopup.classList.remove('show');
        window.location.href = "../Login/login.html"; // Thay "login.html" bằng đường link mong muốn
    }, 4000);
});
