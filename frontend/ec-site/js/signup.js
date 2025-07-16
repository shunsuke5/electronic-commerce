import { signup } from "../../common.js";

const button = document.getElementById("submit-button");

button.addEventListener("click", async () => {
    const form = document.getElementById("customer-form");
    signup(form, "/customer/create", "../html/login.html");
});