import { auth } from "../../common.js";

const productManageBtn = document.getElementById("product-management");
const productCategoryManageBtn = document.getElementById("product-category-management");
const adminManageBtn = document.getElementById("administrator-management");

productManageBtn.addEventListener("click", async () => {
    auth("/admin/auth", "product-index.html");
});

productCategoryManageBtn.addEventListener("click", async () => {
    auth("/admin/auth", "category-index.html");
});

adminManageBtn.addEventListener("click", async () => {
    auth("/admin/auth", "admin-index.html");
});