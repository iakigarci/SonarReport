
let el, stylesTag;

function init() {
  el.innerHTML = `
<div class="page page-limited example-global_page">
  <button class="button button-red" id="example-global_page--button">Do not click me</button>
</div>
`;
  document
    .getElementById("example-global_page--button")
    .addEventListener("click", handleButtonClick);
}

function handleButtonClick() {
  alert("Told you so");
}

export function start(element) {
  el = element;
  init();
}

export function stop() {
  // Remove any event listeners we still have.
  document
    .getElementById("example-global_page--button")
    .removeEventListener("click", handleButtonClick);

  // The node will get removed completely by SonarQube anyway, but we can still
  // clean it up.
  el.innerHTML = "";
}