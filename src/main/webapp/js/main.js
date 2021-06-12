function check_pass() {
    if (document.getElementById('psw').value ==
        document.getElementById('pswConf').value) {
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
     if (document.getElementById('newPassword').value ==
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


