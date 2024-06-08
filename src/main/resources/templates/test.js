document.addEventListener('DOMContentLoaded', () => {
    const fields = {
        username: { input: document.getElementById('username'), valid: false },
        password: { input: document.getElementById('password'), valid: false },
        confirmPassword: { input: document.getElementById('confirmPassword'), valid: false },
        fullName: { input: document.getElementById('fullName'), valid: false },
        address: { input: document.getElementById('address'), valid: false },
        termsAccepted: { input: document.getElementById('termsAccepted'), valid: false },
        privacyPolicyAccepted: { input: document.getElementById('privacyPolicyAccepted'), valid: false }
    };

    const addressInput = document.getElementById('address');
    const zip = document.getElementById('zip');
    const roadAddress = document.getElementById('roadAddress');
    const usernameError = document.getElementById('usernameError');
    const passwordError = document.getElementById('passwordError');
    const registerButton = document.querySelector('input[type="submit"]');

    const updateRegisterButtonState = () => {
        registerButton.disabled = !Object.values(fields).every(field => field.valid);
    };

    const setFieldValid = (field, isValid, message = '') => {
        fields[field].valid = isValid;
        const errorElement = document.getElementById(`${field}Error`);
        if (errorElement) {
            errorElement.textContent = message;
        }
        updateRegisterButtonState();
    };

    const checkUsername = async () => {
        const username = fields.username.input.value;
        try {
            const response = await fetch(`/auth/check-username?username=${encodeURIComponent(username)}`);
            if (!response.ok) {
                const errors = await response.json();
                handleValidationErrors(errors);
                setFieldValid('username', false);
                return;
            }
            setFieldValid('username', true);
        } catch (error) {
            console.error('Error checking username:', error);
            setFieldValid('username', false, '사용자 이름 확인 중 오류가 발생했습니다.');
        }
    };

    const checkPasswordMatch = async () => {
        const password = fields.password.input.value;
        const confirmPassword = fields.confirmPassword.input.value;
        if (password !== confirmPassword) {
            setFieldValid('password', false, '비밀번호가 일치하지 않습니다.');
            setFieldValid('confirmPassword', false);
            return;
        }
        setFieldValid('confirmPassword', true);

        try {
            const response = await fetch('/auth/validatePassword', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ password: password })
            });
            const data = await response.json();
            if (data.valid) {
                setFieldValid('password', true);
            } else {
                setFieldValid('password', false, data.message);
            }
        } catch (error) {
            console.error('Error:', error);
            setFieldValid('password', false, '서버와의 통신 중 오류가 발생했습니다.');
        }
    };

    const handleValidationErrors = (errors) => {
        for (const [field, message] of Object.entries(errors)) {
            setFieldValid(field, false, message);
        }
    };

    fields.username.input.addEventListener('blur', checkUsername);
    fields.password.input.addEventListener('blur', checkPasswordMatch);
    fields.confirmPassword.input.addEventListener('blur', checkPasswordMatch);

    fields.fullName.input.addEventListener('blur', () => {
        const fullName = fields.fullName.input.value.trim();
        if (fullName === '') {
            setFieldValid('fullName', false, '이름을 입력하세요.');
        } else {
            setFieldValid('fullName', true);
        }
    });

    fields.termsAccepted.input.addEventListener('change', () => {
        setFieldValid('termsAccepted', fields.termsAccepted.input.checked);
    });

    fields.privacyPolicyAccepted.input.addEventListener('change', () => {
        setFieldValid('privacyPolicyAccepted', fields.privacyPolicyAccepted.input.checked);
    });

    document.getElementById('searchAddressBtn').addEventListener('click', () => {
        new daum.Postcode({
            oncomplete: (data) => {
                addressInput.value = data.address;
                zip.value = data.zonecode;
                roadAddress.value = data.roadAddress;
                setFieldValid('address', true);
            }
        }).open();
    });

    document.getElementById('registrationForm').addEventListener('submit', (event) => {
        if (!Object.values(fields).every(field => field.valid)) {
            event.preventDefault();
            alert('모든 필드를 올바르게 입력하고 필수 동의 사항을 체크해주세요.');
        }
    });
});
