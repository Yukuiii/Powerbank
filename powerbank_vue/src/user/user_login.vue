<template>
    <div class="bg-gray-100 min-h-screen">
        <section class="py-24">
            <div class="flex flex-col items-center justify-center px-6 py-8 md:h-screen lg:py-0">
                <a class="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white">
                    <svg t="1717161581616" class="icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="2469" width="48" height="48">
                        <path
                            d="M661.20704 622.66368H363.14624a10.96704 10.96704 0 0 1-10.96704-10.97216V276.7616a10.96704 10.96704 0 0 1 10.96704-10.96704h298.0608a10.96704 10.96704 0 0 1 10.97216 10.96704v334.92992a10.96704 10.96704 0 0 1-10.97216 10.97216"
                            fill="#3A72ED" opacity=".2" p-id="2470"></path>
                        <path
                            d="M561.8944 410.50624H501.33504l36.66944-75.97568a24.6016 24.6016 0 0 0-44.31872-21.376l-53.7088 111.26272a24.61184 24.61184 0 0 0 22.14912 35.328h59.904l-35.92192 71.8336a24.69376 24.69376 0 0 0 11.04384 33.03424 24.30976 24.30976 0 0 0 10.9568 2.56h0.09216a24.448 24.448 0 0 0 21.98528-13.59872l53.72928-107.45856a24.6272 24.6272 0 0 0-1.09568-23.9616 24.87808 24.87808 0 0 0-20.93056-11.648m-225.14176 350.7712a18.8672 18.8672 0 0 0 0.29184 37.71904h67.00544a18.87232 18.87232 0 0 0-0.2304-37.71904h-67.072z m-26.01984-33.95584h402.53952a48.5632 48.5632 0 0 1 0 97.12128H310.7328a48.5632 48.5632 0 0 1 0-97.12128m451.072-43.17184a101.56544 101.56544 0 0 0-48.55808-12.3904H310.7328a101.6576 101.6576 0 0 0-48.54784 12.37504V233.40544a34.39616 34.39616 0 0 1 34.58048-33.8432h431.22176a34.24256 34.24256 0 0 1 33.83808 34.67776z m-34.65216-540.16H296.83712A90.50624 90.50624 0 0 0 206.61248 234.24v541.63968a104.2432 104.2432 0 0 0 104.12032 104.12032h402.5344a104.2432 104.2432 0 0 0 104.12032-104.12032V234.21952a90.496 90.496 0 0 0-90.22976-90.21952m-214.6048 617.27744a18.8672 18.8672 0 0 0 0.27648 37.71904h67.03104a18.87232 18.87232 0 0 0-0.22528-37.71904h-67.072z"
                            fill="#3A72ED" p-id="2471"></path>
                    </svg>
                    共享充电宝管理系统
                </a>
                <div
                    class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
                    <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                        <h1
                            class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                            登录
                        </h1>
                        <div class="space-y-4 md:space-y-6">
                            <form @submit.prevent="handleLogin">
                                <div>
                                    <label for="username"
                                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">用户名</label>
                                    <input v-model="username" type="text" name="username" id="username"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        placeholder="" required autocomplete="username">
                                </div>
                                <div>
                                    <label for="password"
                                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">密码</label>
                                    <input v-model="password" type="password" name="password" id="password"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        required autocomplete="current-password">
                                </div>
                                <div class="flex items-center justify-between">
                                    <div class="flex items-start">
                                        <p class="text-red-600" th:text="${error}"></p>
                                    </div>
                                    <a href="#"
                                        class="text-sm font-medium text-blue-600 hover:underline dark:text-primary-500">忘记密码</a>
                                </div>
                                <button type="submit"
                                    class="w-full text-white bg-blue-500 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">
                                    登录
                                </button>
                            </form>
                            <p class="text-sm font-light text-gray-500 dark:text-gray-400">
                                还没有账号？ <a href="/user/register"
                                    class="font-medium text-blue-600 hover:underline dark:text-primary-500">注册</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import fetchRequest from '@api'
import { useRouter } from 'vue-router'
const router = useRouter()

const username = ref('')
const password = ref('')

const handleLogin = async () => {
    const res = await fetchRequest('/user/login', {
        method: 'post',
        body: {
            username: username.value,
            password: password.value
        }
    })
    if (res.code === 200) {
        ElMessage.success({
            message: "登录成功",
            duration: 1000
        })
        localStorage.setItem('user', JSON.stringify(res.data))
        router.push('/user')
    } else {
        ElMessage.error(res.message, { duration: 1000 })
    }
}
</script>

