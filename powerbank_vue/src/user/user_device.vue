<template>
    <div class="bg-gray-100">
        <div class="bg-gradient-to-r from-blue-300 to-blue-200 p-4">
            <div class="flex justify-between items-center">
                <div class="text-lg font-bold flex items-center">
                    <svg t="1717161581616" class="icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="2469" width="48" height="48">
                        <path
                            d="M661.20704 622.66368H363.14624a10.96704 10.96704 0 0 1-10.96704-10.97216V276.7616a10.96704 10.96704 0 0 1 10.96704-10.96704h298.0608a10.96704 10.96704 0 0 1 10.97216 10.96704v334.92992a10.96704 10.96704 0 0 1-10.97216 10.97216"
                            fill="#3A72ED" opacity=".2" p-id="2470"></path>
                        <path
                            d="M561.8944 410.50624H501.33504l36.66944-75.97568a24.6016 24.6016 0 0 0-44.31872-21.376l-53.7088 111.26272a24.61184 24.61184 0 0 0 22.14912 35.328h59.904l-35.92192 71.8336a24.69376 24.69376 0 0 0 11.04384 33.03424 24.30976 24.30976 0 0 0 10.9568 2.56h0.09216a24.448 24.448 0 0 0 21.98528-13.59872l53.72928-107.45856a24.6272 24.6272 0 0 0-1.09568-23.9616 24.87808 24.87808 0 0 0-20.93056-11.648m-225.14176 350.7712a18.8672 18.8672 0 0 0 0.29184 37.71904h67.00544a18.87232 18.87232 0 0 0-0.2304-37.71904h-67.072z m-26.01984-33.95584h402.53952a48.5632 48.5632 0 0 1 0 97.12128H310.7328a48.5632 48.5632 0 0 1 0-97.12128m451.072-43.17184a101.56544 101.56544 0 0 0-48.55808-12.3904H310.7328a101.6576 101.6576 0 0 0-48.54784 12.37504V233.40544a34.39616 34.39616 0 0 1 34.58048-33.8432h431.22176a34.24256 34.24256 0 0 1 33.83808 34.67776z m-34.65216-540.16H296.83712A90.50624 90.50624 0 0 0 206.61248 234.24v541.63968a104.2432 104.2432 0 0 0 104.12032 104.12032h402.5344a104.2432 104.2432 0 0 0 104.12032-104.12032V234.21952a90.496 90.496 0 0 0-90.22976-90.21952m-214.6048 617.27744a18.8672 18.8672 0 0 0 0.27648 37.71904h67.03104a18.87232 18.87232 0 0 0-0.22528-37.71904h-67.072z"
                            fill="#3A72ED" p-id="2471"></path>
                    </svg>
                    坤坤充电宝 | 充电宝租借平台
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div id="modal" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center hidden">
            <div class="bg-white rounded shadow-lg w-11/12 max-w-md mx-auto">
                <div class="border-b p-4">
                    <h2 class="text-lg font-semibold">使用微信扫码进行租借</h2>
                </div>
                <div class="p-4">
                    <div class="modal-body flex justify-center items-center">
                        <img id="qrCodeImage" :src="qrCodeUrl" alt="QR Code" class="img-fluid" />
                    </div>
                </div>
                <div class="border-t p-4 flex justify-end">
                    <button @click="handleQRCodeClose" id="cancel-btn"
                        class="bg-gray-500 text-white px-4 py-2 rounded mr-2">取消</button>
                    <button id="confirm-btn" class="bg-blue-500 text-white px-4 py-2 rounded">确认</button>
                </div>
            </div>
        </div>

        <div class="p-4">
            <div class="text-lg font-bold mb-2">附近可租借设备</div>

            <!-- Device list -->

            <div id="device-list">
                <div v-for="device in devices" :key="device.id"
                    class="device-div bg-white rounded-lg p-4 shadow-md mb-4">
                    <div class="flex">
                        <img :src="device.imgsrc" alt="Shop Image" class="h-full w-24 rounded-lg object-cover">
                        <div class="ml-4 w-full">
                            <div class="flex justify-between items-center">
                                <div class="text-lg font-bold">{{ '充电宝 ' + device.id + '号' }}</div>
                                <span class="bg-red-400 text-white text-xs px-2 py-1 rounded-full">
                                    {{ device.distance + 'km' }}
                                </span>
                            </div>
                            <div class="text-xs text-gray-600 mt-2">
                                {{ '设备ID:' + device.deviceId }}
                            </div>
                            <div class="text-sm text-gray-600">⏰ 00:00-24:00</div>
                            <div class="text-sm text-gray-600">
                                {{ '📍 ' + device.location }}
                            </div>
                            <div class="flex mt-2">
                                <span class="bg-green-100 text-green-500 text-xs px-2 py-1 rounded mr-2">可借</span>
                                <span class="bg-blue-100 text-blue-500 text-xs px-2 py-1 rounded">可还</span>
                            </div>
                        </div>
                    </div>
                    <div class="text-xs text-gray-600 mt-2">
                        前3分钟内免费,5元/1小时,不足1小时按1小时计算,39元/24小时,总封顶99元
                    </div>
                    <div class="flex justify-end text-sm mt-2">
                        <button @click="handleRent(device.deviceId)"
                            class="bg-cyan-500 text-white px-4 py-2 rounded mr-2">手动租借</button>
                        <button @click="handleScan(device.deviceId)"
                            class="bg-cyan-500 text-white px-4 py-2 rounded">扫码租借</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script setup>
import { onMounted, ref } from 'vue';
import fetchRequest from '@api';
import { useRouter } from 'vue-router';

const router = useRouter()

const devices = ref([])
const qrCodeUrl = ref('')
const user = ref(JSON.parse(localStorage.getItem('user')))
var timer;
onMounted(async () => {
    const res = await fetchRequest('/user/device/get');
    devices.value = res;
});


//手动租借
const handleRent = async (deviceId) => {
    const res = await fetchRequest('/user/device/rent?deviceId=' + deviceId + '&userId=' + user.value.userId);
    if (res.code == 200) {
        ElMessage.success({
            message: '租借成功',
            duration: 1000
        });
        router.push('/user/order');
    } else {
        ElMessage.error(res.message);
    }

}

//扫码租借
const handleScan = (deviceId) => {
    const host = "https://springboot.yukuii.cn/v1"
    const url = `${host}/user/qrcode/rent?deviceId=${deviceId}&userId=${user.value.userId}`
    handleQRCode(url)
     timer = setInterval(async () => {
        const res = await fetchRequest('/user/qrcode/isScan?deviceId=' + deviceId)
        console.log(res.message)
        if (res.code == 200) {
            ElMessage.success("租借成功")
            clearInterval(timer)
            router.push('/user/order', { tab: 'tab-1' })
        }
    }, 3000)
}


const handleQRCode = (url) => {
    const qrcodeUrl = "https://springboot.yukuii.cn/v1/user/qrcode/get?url=" + encodeURIComponent(url)
    qrCodeUrl.value = qrcodeUrl
    const modal = document.getElementById('modal');
    modal.classList.remove('hidden');
}


const handleQRCodeClose = () => {
    const modal = document.getElementById('modal');
    modal.classList.add('hidden');
    clearInterval(timer)
}






</script>
