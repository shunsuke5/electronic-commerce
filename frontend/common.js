import { BACK_ORIGIN } from "./constant.js";

export async function signUp(form, path, destPage) {
    const formData = new FormData(form);

    try {
        const response = await fetch(BACK_ORIGIN + path, {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            window.location.href = generateHref(destPage);
        } else if (response.status === 401) {
            alert("認証失敗");
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
}

export async function sendForm(form, path, destPage) {
    const formData = new FormData(form);

    try {
        const response = await fetch(BACK_ORIGIN + path, {
            method: "POST",
            body: formData,
            credentials: "include"
        });

        if (response.ok) {
            window.location.href = generateHref(destPage);
        } else if (response.status === 401) {
            alert("認証失敗");
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
}

export async function isValidToken(path) {
    try {
        const response = await fetch(BACK_ORIGIN + path, {
            method: "GET",
            credentials: "include"
        });

        if (response.ok) {
            return true;
        } else if (response.status === 403) {
            window.location.href = "login.html";
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
}

export async function auth(path, destPage) {
    try {
        const response = await fetch(BACK_ORIGIN + path, {
            method: "GET",
            credentials: "include"
        });

        if (response.ok) {
            window.location.href = generateHref(destPage);
        } else if (response.status === 403) {
            window.location.href = "login.html";
        } else {
            alert("エラー:" + response.statusText);
        }
    } catch (error) {
        console.error("通信エラー:", error);
    }
}

function generateHref(destPage) {
    const href = window.location.href;
    const index = href.lastIndexOf("/");
    return href.substring(0, index + 1) + destPage;
}