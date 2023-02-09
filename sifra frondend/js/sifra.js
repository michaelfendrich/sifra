let wordToCode;
let password;
let submitButton;
let wordText;
let passwordText;

window.onload = function() {
    submitButton = document.getElementById("submitButton");
    wordToCode = document.getElementById("wordToCode");
    password = document.getElementById("password");
    wordToCode.addEventListener("input", buttonDisabled);
    password.addEventListener("input", buttonDisabled);
    document.addEventListener("keypress", (event)=> {
        if (event.keyCode === 13) {
          event.preventDefault();
            if (wordText && passwordText) {
                performOperation();
            }
        }
    });
    submitButton.onclick = performOperation;
}

function performOperation() {
    let radioButton = document.querySelector('input[type="radio"]');
    let typeOfOperation = radioButton.checked ? 1 : 2;
    let data = {
        text: wordToCode.value,
        password: password.value,
        operation: typeOfOperation
    };
    console.log(data);
    let baseURL = "http://localhost:8080/";
    let xhr = new XMLHttpRequest();
    xhr.onload = () => {
        let res = JSON.parse(xhr.response);
        console.log(res);
        alert(res.code);
    }
    xhr.open("POST", baseURL + "code");
    xhr.setRequestHeader('Access-Control-Allow-Headers', '*');
    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhr.setRequestHeader('Access-Control-Allow-Methods', 'POST');
    xhr.setRequestHeader('Access-Control-Allow-Credentials', true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(data));
}

function buttonDisabled() {
    wordText = removeSpecificSymbols(wordToCode.value);
    console.log(wordText);
    passwordText = removeSpecificSymbols(password.value);
    console.log(passwordText);
    submitButton.disabled = (!wordText || !passwordText) ? true : false;
}

function removeSpecificSymbols(element) {
    element = element.toLowerCase().replace("ł", "l");
    return element.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
                .replace(/[.,\/#!$%\^&\*;:{}=\-_`~()@0123456789|<>"'\\?[\]]/g,"").toLowerCase().trim();
}
