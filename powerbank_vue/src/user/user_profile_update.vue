<template>
    <div class="bg-white">
        <div class="max-w-md mx-auto">
            <div class="p-4 bg-blue-400">
                <h2 class="text-2xl font-bold text-gray-900">个人信息</h2>
            </div>
            <div class="border-t">
                <div class="flex items-center justify-between p-4 border-b">
                    <span>头像</span>
                    <img class="w-12 h-12 rounded-full" :src="user.avatarimgsrc" alt="Avatar">
                </div>
                <div class="flex items-center justify-between p-4 border-b">
                    <span>昵称</span>
                    <input type="text" class="editable-div focus:outline-none text-gray-500 border-none"
                        v-model="user.username" id="username">
                </div>
                <div class="flex items-center justify-between p-4 border-b">
                    <span>密码</span>
                    <input type="password" class="text-gray-500 focus:outline-none border-none" v-model="user.password"
                        id="password">
                </div>
                <div class="flex items-center justify-between p-4 border-b">
                    <span>电子邮箱</span>
                    <input type="email" id="email" class="focus:outline-none text-gray-500 border-none"
                        v-model="user.email">
                </div>
                <div class="flex items-center justify-center p-4 mt-10">
                    <button class="px-4 py-2 text-white bg-blue-500 rounded-md hover:bg-blue-600 focus:outline-none"
                        @click="updateProfile">保存更改
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import fetchRequest from '@api'
import { useRouter } from 'vue-router'

const router = useRouter()

const user = ref({
})

onMounted(() => {
    user.value = JSON.parse(localStorage.getItem('user'))
})

const updateProfile = async () => {
    const res = await fetchRequest('/user/update', {
        method: 'post',
        body: user.value
    })
    if (res.code === 200) {
        localStorage.setItem('user', JSON.stringify(user.value))
        ElMessage.success('更新成功')
        router.push('/user/profile')
    }
}
</script>
