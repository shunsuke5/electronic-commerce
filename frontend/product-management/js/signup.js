import { signUp } from "../../common.js";

const button = document.getElementById("submit-button");

button.addEventListener("click", async () => {
    const form = document.getElementById("admin-form");
    signUp(form, "/admin/create", "login.html");
});