<template>
    <div class="bg-gray-100">
        <div class="container mx-auto p-4">
            <div class="bg-blue-400 text-white shadow-lg overflow-hidden w-full rounded-xl shadow-md">
                <div class="flex items-center p-4">
                    <img class="w-16 h-16 rounded-full" :src="user.avatarimgsrc" alt="User Avatar">
                    <div class="ml-6">
                        <div class="text-lg font-bold">{{ user.username }}</div>
                        <div class="text-sm mt-1">余额:{{ user.balance }}元</div>
                        <div class="text-sm mt-1">注册时间:{{ user.registrationTime }}</div>
                    </div>
                </div>
                <div class="flex justify-center items-center border-t border-gray-200">
                    <a @click="handleClick('/user/recharge')"
                        class="w-full text-white text-center font-medium text-base px-5 py-2.5 dark:bg-blue-600">充值
                    </a>
                </div>
            </div>
            <div id="mega-menu-icons"
                class="items-center justify-between w-full  bg-white rounded-xl shadow-md md:flex md:w-auto md:order-1">
                <ul class="flex flex-col mt-4 font-medium md:flex-row md:mt-0 md:space-x-8 rtl:space-x-reverse">
                    <li @click="handleClick('/user/profile_update')"
                        class="h-[64px] text-base flex items-center justify-between w-full py-2 px-3 font-medium text-gray-900 border-b border-gray-100 md:w-auto hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-600 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-blue-500 md:dark:hover:bg-transparent dark:border-gray-700">
                        个人信息
                        <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="m9 5 7 7-7 7" />
                        </svg>
                    </li>
                    <li @click="handleClick('/user/order',{tab:'tab-1'})"
                        class="h-[64px] text-base flex items-center justify-between w-full py-2 px-3 font-medium text-gray-900 border-b border-gray-100 md:w-auto hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-600 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-blue-500 md:dark:hover:bg-transparent dark:border-gray-700">
                        正在进行中订单
                        <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="m9 5 7 7-7 7" />
                        </svg>
                    </li>
                    <li @click="handleClick('/user/order',{tab:'tab-3'})"
                        class="h-[64px] text-base flex items-center justify-between w-full py-2 px-3 font-medium text-gray-900 border-b border-gray-100 md:w-auto hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-600 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-blue-500 md:dark:hover:bg-transparent dark:border-gray-700">
                        未支付订单
                        <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="m9 5 7 7-7 7" />
                        </svg>
                    </li>
                    <li @click="handleClick('/user/order',{tab:'tab-2'})"
                        class="h-[64px] text-base flex items-center justify-between w-full py-2 px-3 font-medium text-gray-900 border-b border-gray-100 md:w-auto hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-600 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-blue-500 md:dark:hover:bg-transparent dark:border-gray-700">
                        已完成订单
                        <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="m9 5 7 7-7 7" />
                        </svg>
                    </li>
                    <li @click="handleLogout"
                        class="h-[64px] text-base flex items-center justify-between w-full py-2 px-3 font-medium text-gray-900 border-b border-gray-100 md:w-auto hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-600 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-blue-500 md:dark:hover:bg-transparent dark:border-gray-700">
                        退出登录
                        <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="m9 5 7 7-7 7" />
                        </svg>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'
import fetchRequest from '@api'
const router = useRouter()

const user = ref({})

onMounted(async () => {
    user.value = JSON.parse(localStorage.getItem('user'))
    const res = await fetchRequest('/user/get/' + user.value.userId)
    user.value = res.data
    localStorage.setItem('user', JSON.stringify(user.value))
})

const handleClick = (path, query) => {
    if(query){
        router.push({ path: path, query: query })
    }else{
        router.push(path)
    }
}

const handleLogout = () => {
    localStorage.removeItem('user')
    router.push('/user/login')
}
</script>