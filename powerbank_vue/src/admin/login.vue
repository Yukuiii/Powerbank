<template>
    <div class="bg_img bg-cover bg-center flex items-center justify-center h-screen">
        <div class="bg-white p-8 rounded-lg shadow-lg w-96">
            <h2 class="text-2xl font-bold mb-6 text-center">登录</h2>
            <form>
                <div class="mb-4">
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="username">
                        用户名
                    </label>
                    <input v-model="username"
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        id="username" type="text" placeholder="请输入用户名">
                </div>
                <div class="mb-6">
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="password">
                        密码
                    </label>
                    <input v-model="password"
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
                        id="password" type="password" placeholder="请输入密码" autocomplete
                    >
                </div>
                <div class="flex items-center justify-between">
                    <button @click="login"
                        class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        type="button">
                        登录
                    </button>
                    <a class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800" href="#">
                        忘记密码?
                    </a>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import fetchRequest from '@api';
const username = ref('')
const password = ref('')
const router = useRouter()
const login = async () => {
    if (username.value.trim() === '' || password.value.trim() === '') {
        alert('用户名和密码不能为空');
        return;
    }
    try {
        const res = await fetchRequest('/admin/user/login', {
            method: 'POST',
            body: {
                username: username.value,
                password: password.value
            }
        });
        if (res.code === 200) {
            ElMessage.success("登录成功")
            localStorage.setItem('users', JSON.stringify(res.data))
            router.push('/dashboard');
        } else {
            ElMessage.error(res.message)
        }
    } catch (error) {
        console.error('Error:', error);
        alert('请求失败，请检查网络或联系管理员');
    }
}

</script>

<style lang="css" scoped>
.bg_img {
    background-image: url('https://ae01.alicdn.com/kf/S0b1c334155aa40c38dfa758919c91c83t.jpg');
    background-size: cover;
    /* 使背景图片覆盖整个页面 */
    background-repeat: no-repeat;
    /* 防止背景图片重复 */
    background-position: center;
    /* 将背景图片居中 */
}
</style>
