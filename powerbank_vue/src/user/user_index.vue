<template>
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
<div class="p-4">
    <div class="bg-white rounded-lg p-2 shadow-md">
        <img src="https://ae01.alicdn.com/kf/S703c68e91dc64a2ca6d56a39a8380c44q.png" alt="Map"
             class="rounded-lg">
    </div>
</div>
<a @click="handleClick">
    <div class="p-4">
        <div class="bg-blue-400 text-white text-center py-3 rounded-md shadow-md font-bold">
            🚀 点击租用
        </div>
    </div>
</a>
<div class="p-4">
    <div class="flex space-x-2">
        <div class="bg-white rounded-lg flex-1 p-4 shadow-md">
            <div class="text-blue-500 font-bold">广告位招租</div>
            <div class="text-sm text-gray-600">广告位招租</div>
        </div>
        <div class="bg-white rounded-lg flex-1 p-4 shadow-md">
            <div class="text-blue-500 font-bold">广告位招租</div>
            <div class="text-sm text-gray-600">广告位招租</div>
        </div>
    </div>
</div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

const handleClick = ()=>{
    router.push('/user/device')
}

</script>