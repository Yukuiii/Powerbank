<template>
    <div class="fixed bottom-0 left-0 w-full bg-gray-50 shadow-md">
        <div class="flex justify-around py-1">
            <a @click="handleClick('/user',$event)"  class="nav-item  flex flex-col items-center text-blue-400">
                <svg width="24" height="24" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M9 18V42H39V18L24 6L9 18Z" fill="none" stroke="#333" stroke-width="4"
                        stroke-linecap="round" stroke-linejoin="round" />
                    <path d="M19 29V42H29V29H19Z" fill="none" stroke="#333" stroke-width="4" stroke-linejoin="round" />
                    <path d="M9 42H39" stroke="#333" stroke-width="4" stroke-linecap="round" />
                </svg>
                <span class="text-xs mt-1">首页</span>
            </a>
            <a  @click="handleClick('/user/order',$event)" class="nav-item text-gray-600  flex flex-col items-center">
                <svg width="24" height="24" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M24 40C32.8366 40 40 32.8366 40 24C40 15.1634 32.8366 8 24 8C15.1634 8 8 15.1634 8 24C8 32.8366 15.1634 40 24 40Z"
                        fill="none" stroke="#333" stroke-width="4" stroke-linejoin="round" />
                    <path
                        d="M37.5641 15.5098C41.7833 15.878 44.6787 17.1724 45.2504 19.306C46.3939 23.5737 37.8068 29.5827 26.0705 32.7274C14.3343 35.8721 3.89316 34.9617 2.74963 30.694C2.1505 28.458 4.22245 25.744 8.01894 23.2145"
                        stroke="#333" stroke-width="4" stroke-linecap="round" stroke-linejoin="round" />
                </svg>
                <span class="text-xs mt-1">我的订单</span>
            </a>

            <a @click="handleClick('/user/profile',$event)" class="nav-item text-gray-600  flex flex-col items-center">
                <svg width="24" height="24" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path
                        d="M24 44C35.0457 44 44 35.0457 44 24C44 12.9543 35.0457 4 24 4C12.9543 4 4 12.9543 4 24C4 35.0457 12.9543 44 24 44Z"
                        fill="none" stroke="#333" stroke-width="4" stroke-linejoin="round" />
                    <path d="M31 31C31 31 29 35 24 35C19 35 17 31 17 31" stroke="#333" stroke-width="4"
                        stroke-linecap="round" stroke-linejoin="round" />
                    <path d="M31 18V22" stroke="#333" stroke-width="4" stroke-linecap="round" stroke-linejoin="round" />
                    <path d="M17 18V22" stroke="#333" stroke-width="4" stroke-linecap="round" stroke-linejoin="round" />
                </svg>
                <span class="text-xs mt-1">个人中心</span>
            </a>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

const handleClick = (path,event) => {
    router.push(path)
    document.querySelectorAll('.nav-item').forEach(item => {
        item.classList.remove('text-blue-400');
        item.classList.add('text-gray-600');
    });
    event.currentTarget.classList.remove('text-gray-600');
    event.currentTarget.classList.add('text-blue-400');
}

</script>