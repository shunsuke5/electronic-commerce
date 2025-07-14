import { BASE_URL } from "./constant.js";

const button = document.getElementById("submit-button");

button.addEventListener("click", async () => {
    console.log("button pushed")
    const form = document.getElementById("admin-form");
    const formData = new FormData(form);

    try {
        const response = await fetch(BASE_URL + "/admin/create", {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            console.log("送信成功");
        } else if (response.status === 401) {
            alert("認証失敗");
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
});