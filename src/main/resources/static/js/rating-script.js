let star = document.querySelectorAll('input');

let showvValue = document.querySelector('#rating-value');

for(let i = 0; i < star.length; i++){
    star[i].addEventListener('click', function(){
        i = this.value;

        showvValue.innerHTML = i + " out of 5";
    })
}