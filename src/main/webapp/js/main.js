function check_pass() {
    if (document.getElementById('psw').value ===
        document.getElementById('pswConf').value && document.getElementById('psw').value.length > 0 ) {
        document.getElementById('submit').disabled = false;
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'Confirmation ok';

    }else {
        document.getElementById('submit').disabled = true;
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = 'Erreur de confirmation';
    }
}
function check_pass_profil(){
     if (document.getElementById('newPassword').value ===
        document.getElementById('newPassConf').value) {
        document.getElementById('submit').disabled = false;
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = 'Confirmation ok';
    }else {
         document.getElementById('submit').disabled = true;
         document.getElementById('message').style.color = 'red';
         document.getElementById('message').innerHTML = 'Erreur de confirmation';
     }
}

function validatePseudo(){
    var pseudo = document.getElementById('pseudo').value;
    if( /[^a-zA-Z0-9]/.test( pseudo ) ) {
        document.getElementById('submit').disabled = false;
        document.getElementById('pseudoValid').style.color = 'red';
        document.getElementById('pseudoValid').innerHTML = 'Caractère alphanumérique uniquement';
        return false;
    }else {
        document.getElementById('submit').disabled = true;
        document.getElementById('pseudoValid').style.color = 'green';
        document.getElementById('pseudoValid').innerHTML = '';
    }
    return true;
}


