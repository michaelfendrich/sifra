let slovo;
let heslo;
let tlacitko;
let slovoText;
let hesloText;

window.onload = function() {
    tlacitko = document.getElementById("tlacitko");
    slovo = document.getElementById("slovo");
    heslo = document.getElementById("heslo");
    slovo.addEventListener("input", tlacitkoDisabled);
    heslo.addEventListener("input", tlacitkoDisabled);
    document.addEventListener("keypress", (event)=> {
        if (event.keyCode === 13) {
          event.preventDefault();
            if (slovoText && hesloText) {
                provedOperaci();
            }
        }
    });
    tlacitko.onclick = provedOperaci;
}

function provedOperaci() {
    let radioButton = document.querySelector('input[type="radio"]');
    let cisloOperace = radioButton.checked ? 1 : 2;
    let data = {
        text: slovo.value,
        heslo: heslo.value,
        operace: cisloOperace
    };
    console.log(data);
    let baseURL = "http://localhost:8080/";
    let xhr = new XMLHttpRequest();
    xhr.onload = () => {
        let res = JSON.parse(xhr.response);
        console.log(res);
        alert(res.sifra);
    }
    xhr.open("POST", baseURL + "sifra");
    xhr.setRequestHeader('Access-Control-Allow-Headers', '*');
    xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
    xhr.setRequestHeader('Access-Control-Allow-Methods', 'POST');
    xhr.setRequestHeader('Access-Control-Allow-Credentials', true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(data));
}

function tlacitkoDisabled() {
    slovoText = odstraneniDiakritiky(slovo.value);
    hesloText = odstraneniDiakritiky(heslo.value);
    tlacitko.disabled = (!slovoText || !hesloText) ? true : false;
}

function odstraneniDiakritiky(element) {
    element = element.toLowerCase().replace("ł", "l");
    return element.normalize("NFD").replace(/[\u0300-\u036f]/g, "")
                .replace(/[.,\/#!$%\^&\*;:{}=\-_`~()@0123456789|<>"'\\?[\]]/g,"").toLowerCase().trim();
}
