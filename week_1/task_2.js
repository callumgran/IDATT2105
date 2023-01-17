var count = 0;
var prev = true;

const words = ["Hei", "Fisk", "Arne", "Bil", "Hus"];
const wrapper = document.getElementById("jscontent");
const counter = document.getElementById("counter");
const hide_show = document.getElementById("hide_show");
const footer = document.getElementById("footer");

counter.onclick = () => {
  count++;
  counter_num.innerText = count;
};

hide_show.onclick = () => {
  if (prev) {
    wrapper.style.display = "none";
    prev = false;
  } else {
    wrapper.style.display = "";
    prev = true;
  }
};

window.onload = () => {
  words.forEach(e => {
    const div = document.createElement("div");
    div.className = "footer_elem";
    const div2 = document.createElement("div");
    div2.appendChild(document.createTextNode(e));
    div.appendChild(div2);
    footer.appendChild(div);
  });
}
