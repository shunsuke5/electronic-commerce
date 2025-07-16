import { login } from "../../common.js";

const button = document.getElementById("submit-button");

button.addEventListener("click", async () => {
    const form = document.getElementById("customer-form");
    login(form, "/token/customer", "../html/index.html");
});