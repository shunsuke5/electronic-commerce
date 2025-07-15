import { BASE_URL } from "./constant.js";

const button = document.getElementById("product-management");

button.addEventListener("click", async () => {
    try {
        const response = await fetch(BASE_URL + "/auth", {
            method: "GET",
            credentials: "include"
        });

        if (response.ok) {
            window.location.href = "product-index.html";
        } else if (response.status === 403) {
            window.location.href = "login.html";
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
});