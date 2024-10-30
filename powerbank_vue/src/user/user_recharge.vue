<template>
    <div class="bg-white flex justify-center items-center min-h-screen">
        <div class="w-full max-w-md mx-auto p-4 bg-white rounded-lg shadow-md">
            <div class="flex justify-between items-center mb-6">
                <h1 class="text-2xl font-bold">充值金额</h1>
            </div>

            <!-- 金额选项 -->
            <div class="grid grid-cols-2 gap-4 mb-6">
                <button v-for="amount in amounts" :key="amount"
                    :class="['amount-button', 'w-full', 'py-3', 'rounded-lg', 'border-2', selectedAmount === amount ? 'bg-blue-500 text-white' : 'bg-gray-100 text-gray-700']"
                    @click="selectAmount(amount)">
                    充值 ¥ {{ amount }}
                </button>
            </div>
            <div class="flex justify-center">
                <button @click="handleRecharge" id="rechargeButton"
                    class="w-full py-3 bg-red-500 text-white rounded-lg text-lg font-bold">
                    立即充值
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import fetchRequest from '@api'
import { useRouter } from 'vue-router'
const router = useRouter()

const amounts = [100, 50, 20, 10, 5]
const selectedAmount = ref(null)

function selectAmount(amount) {
    selectedAmount.value = amount
}

const handleRecharge = async () => {
    if (!selectedAmount.value) {
        ElMessage.error("请选择充值金额")
        return
    }
    const user = JSON.parse(localStorage.getItem('user'))
    const res = await fetchRequest('/user/recharge', {
        method: 'POST',
        body: {
            amount: selectedAmount.value,
            userId: user.userId
        }
    })
    if (res.code === 200) {
        ElMessage.success("充值成功")
        router.push('/user/profile')
    } else {
        ElMessage.error(res.message)
    }
}
</script>
