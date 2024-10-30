<template>
    <div class="bg-gray-100">
        <div class="container mx-auto p-4">
            <div class="flex justify-around bg-blue-400 text-white rounded-lg shadow-md mb-4">
                <button v-for="tab in ['tab-1', 'tab-2', 'tab-3']" :key="tab" :id="tab"
                    :class="['tab-button', 'px-4', 'py-2', 'focus:outline-none', { 'border-b-4 border-white-500': activeTab === tab }]"
                    @click="switchTab(tab)">
                    {{ tab === 'tab-1' ? '进行中' : tab === 'tab-2' ? '已完成' : '未支付' }}
                </button>
            </div>

            <!-- 进行中 -->
            <div v-show="activeTab === 'tab-1'" id="page-1" class="tab-content">
                <div v-if="isGoingorder == null">
                    <div class="bg-white rounded-lg shadow-md p-4 mb-4 relative">
                        暂无订单信息
                    </div>
                </div>
                <div v-else>
                    <div class="bg-white rounded-lg shadow-md p-4 mb-4 relative">
                        <div class="absolute top-2 right-3">
                            <svg class="w-6 h-6 text-blue-300" fill="currentColor" xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24">
                                <path
                                    d="M12 0C5.383 0 0 5.383 0 12s5.383 12 12 12 12-5.383 12-12S18.617 0 12 0zm1.5 17h-3v-2h3v2zm0-4h-3V7h3v6z" />
                            </svg>
                        </div>
                        <h2 class="text-xl font-bold mb-2 text-blue-300">进行中</h2>
                        <div class="flex items-center mb-2">
                            <svg width="24" height="24" viewBox="0 0 48 48" fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <path d="M19 4H37L26 18H41L17 44L22 25H8L19 4Z" fill="none" stroke="#333"
                                    stroke-width="4" stroke-linejoin="round" />
                            </svg>
                            <span class="ml-2">订单号:</span>
                            <span class="text-gray-700">{{ isGoingorder.orderNumber }}</span>
                        </div>
                        <div class="flex items-center mb-2">
                            <svg width="24" height="24" viewBox="0 0 48 48" fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M24 44C35.0457 44 44 35.0457 44 24C44 12.9543 35.0457 4 24 4C12.9543 4 4 12.9543 4 24C4 35.0457 12.9543 44 24 44Z"
                                    fill="none" stroke="#333" stroke-width="4" stroke-linejoin="round" />
                                <path d="M24.0084 12.0001L24.0072 24.0089L32.4866 32.4883" stroke="#333"
                                    stroke-width="4" stroke-linecap="round" stroke-linejoin="round" />
                            </svg>
                            <span class="ml-2">开始时间:</span>
                            <span class="text-gray-700">{{ isGoingorder.startTime }}</span>
                        </div>
                        <div class="flex items-center">
                            <svg width="24" height="24" viewBox="0 0 48 48" fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M24 44C35.0457 44 44 35.0457 44 24C44 12.9543 35.0457 4 24 4C12.9543 4 4 12.9543 4 24C4 35.0457 12.9543 44 24 44Z"
                                    fill="none" stroke="#333" stroke-width="4" stroke-linejoin="round" />
                                <path d="M18 22H30" stroke="#333" stroke-width="4" stroke-linecap="round"
                                    stroke-linejoin="round" />
                                <path d="M18 28H30" stroke="#333" stroke-width="4" stroke-linecap="round"
                                    stroke-linejoin="round" />
                                <path d="M24.0083 22V34" stroke="#333" stroke-width="4" stroke-linecap="round"
                                    stroke-linejoin="round" />
                                <path d="M30 15L24 21L18 15" stroke="#333" stroke-width="4" stroke-linecap="round"
                                    stroke-linejoin="round" />
                            </svg>
                            <span class="ml-2">金额:</span>
                            <span class="text-green-700">{{ newBalance }}</span>
                            <span>元</span>
                        </div>
                        <button @click="handleReturn(isGoingorder.orderNumber, isGoingorder.deviceId)"
                            class="absolute bottom-2 right-3 bg-blue-400 text-white px-2 py-1 rounded-lg">去归还
                        </button>
                    </div>
                </div>
            </div>
            <!-- 已完成 -->
            <div v-show="activeTab === 'tab-2'" id="page-2" class="tab-content">
                <div v-if="!isCompletedorder.length">
                    <div class="bg-white rounded-lg shadow-md p-4 mb-4 relative">
                        暂无订单信息
                    </div>
                </div>
                <div v-else>
                    <div v-for="order in isCompletedorder" :key="order.orderNumber"
                        class="bg-white rounded-lg shadow-md p-4 mb-4 relative">
                        <div class="absolute top-2 right-3">
                            <svg class="w-6 h-6 text-blue-300" fill="currentColor" xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24">
                                <path
                                    d="M12 0C5.383 0 0 5.383 0 12s5.383 12 12 12 12-5.383 12-12S18.617 0 12 0zm1.5 17h-3v-2h3v2zm0-4h-3V7h3v6z" />
                            </svg>
                        </div>
                        <h2 class="text-xl font-bold mb-2 text-blue-300">已完成</h2>
                        <div class="flex items-center mb-2">
                            <svg width="24" height="24" viewBox="0 0 48 48" fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <path d="M19 4H37L26 18H41L17 44L22 25H8L19 4Z" fill="none" stroke="#333"
                                    stroke-width="4" stroke-linejoin="round" />
                            </svg>
                            <span class="ml-2">订单号:</span>
                            <span class="text-gray-700">{{ order.orderNumber }}</span>
                        </div>
                        <div class="flex items-center mb-2">
                            <svg width="24" height="24" viewBox="0 0 48 48" fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M24 44C35.0457 44 44 35.0457 44 24C44 12.9543 35.0457 4 24 4C12.9543 4 4 12.9543 4 24C4 35.0457 12.9543 44 24 44Z"
                                    fill="none" stroke="#333" stroke-width="4" stroke-linejoin="round" />
                                <path d="M24.0084 12.0001L24.0072 24.0089L32.4866 32.4883" stroke="#333"
                                    stroke-width="4" stroke-linecap="round" stroke-linejoin="round" />
                            </svg>
                            <span class="ml-2">完成时间:</span>
                            <span class="text-gray-700">{{ order.endTime }}</span>
                        </div>
                        <div class="flex items-center">
                            <svg width="24" height="24" viewBox="0 0 48 48" fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M24 44C35.0457 44 44 35.0457 44 24C44 12.9543 35.0457 4 24 4C12.9543 4 4 12.9543 4 24C4 35.0457 12.9543 44 24 44Z"
                                    fill="none" stroke="#333" stroke-width="4" stroke-linejoin="round" />
                                <path d="M18 22H30" stroke="#333" stroke-width="4" stroke-linecap="round"
                                    stroke-linejoin="round" />
                                <path d="M18 28H30" stroke="#333" stroke-width="4" stroke-linecap="round"
                                    stroke-linejoin="round" />
                                <path d="M24.0083 22V34" stroke="#333" stroke-width="4" stroke-linecap="round"
                                    stroke-linejoin="round" />
                                <path d="M30 15L24 21L18 15" stroke="#333" stroke-width="4" stroke-linecap="round"
                                    stroke-linejoin="round" />
                            </svg>
                            <span class="ml-2">金额：</span>
                            <span class="text-green-700">{{ order.amount }}</span>
                            <span>元</span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 未支付 -->
            <div v-show="activeTab === 'tab-3'" id="page-3" class="tab-content">
                <div v-if="!isOrderNoPay.length">
                    <div class="bg-white rounded-lg shadow-md p-4 mb-4 relative">
                        暂无订单信息
                    </div>
                </div>
                <div v-else>
                    <div v-for="order in isOrderNoPay" :key="order.device_id"
                        class="bg-white rounded-lg shadow-md p-4 mb-4 flex justify-between items-center">
                        <div>
                            <div class="flex items-center mb-2">
                                <span class="w-3 h-3 bg-red-500 rounded-full mr-2"></span>
                                <span class="text-black font-semibold">{{ order.end_time }}</span>
                            </div>
                            <div class="text-black mb-1">待支付 <span class="text-gray-500">{{ order.amount }}</span>
                            </div>
                            <div class="text-gray-500">{{ order.device_id }}</div>
                        </div>
                        <button class="bg-red-500 text-white px-4 py-2 rounded">去支付</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import fetchRequest from '@api'


const activeTab = ref('tab-1');
const isGoingorder = ref(null)
const isCompletedorder = ref([])
const isOrderNoPay = ref([])
const user = ref(JSON.parse(localStorage.getItem('user')))
const newBalance = ref(0)
var timer

onMounted(async () => {
    const query = new URLSearchParams(window.location.search);
    const tab = query.get('tab');
    if (tab) {
        activeTab.value = tab;
    }
    handleRefresh()
    if (isGoingorder.value) {
        newBalance.value = isGoingorder.value.amount
    }
});

const handleRefresh = async () => {
    const completeres = await fetchRequest('/user/order/get/complete/' + user.value.userId);
    isCompletedorder.value = completeres.data
    const goingres = await fetchRequest('/user/order/get/going/' + user.value.userId);
    isGoingorder.value = goingres.data
}

const switchTab = (tabId) => {
    activeTab.value = tabId;
};

const handleReturn = async (orderNumber, deviceId) => {
    clearInterval(timer)
    newBalance.value = 0
    const res = await fetchRequest('/user/device/retuen', {
        method: 'POST',
        body: {
            orderNumber: orderNumber,
            deviceId: deviceId
        }
    });
    if (res.code == 200) {
        ElMessage.success({
            message: '归还成功',
            duration: 1000
        });
        handleRefresh()
        activeTab.value = 'tab-2'
    } else {
        ElMessage.error(res.message);
    }
}


timer = setInterval(() => {
    newBalance.value += 5
}, 1000)


</script>
