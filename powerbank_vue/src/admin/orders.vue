<template>
    <div class="container mx-auto p-6 bg-white-100 min-h-screen">
        <h1 class="text-3xl font-bold mb-6 text-center text-gray-800">订单管理</h1>

        <div class="flex justify-end mb-4">
            <button @click="dialogAddVisible = true"
                class="bg-cyan-500 hover:bg-cyan-600 text-white px-4 py-2 rounded transition-colors duration-200">
                添加订单
            </button>
        </div>
        <el-dialog v-model="dialogUpdateVisible" title="修改订单" width="500">
            <el-form :model="updateForm">
                <el-form-item label-position="left" label-width="80" label="订单号">
                    <el-input v-model="updateForm.orderNumber" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="用户ID">
                    <el-input v-model="updateForm.userId" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="设备ID">
                    <el-input v-model="updateForm.deviceId" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="状态">
                    <el-input v-model="updateForm.status" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="开始时间">
                    <el-input v-model="updateForm.startTime" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="结束时间">
                    <el-input v-model="updateForm.endTime" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="金额">
                    <el-input v-model="updateForm.amount" autocomplete="off" />
                </el-form-item>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogUpdateVisible = false">取消修改</el-button>
                    <el-button type="primary" @click="handleUpdate">
                        确认修改
                    </el-button>
                </div>
            </template>
        </el-dialog>
        <el-dialog v-model="dialogAddVisible" title="添加订单" width="500">
            <el-form :model="addForm">
                <el-form-item label-position="left" label-width="80" label="订单号">
                    <el-input v-model="addForm.orderNumber" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="用户ID">
                    <el-input v-model="addForm.userId" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="设备ID">
                    <el-input v-model="addForm.deviceId" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="状态">
                    <el-input v-model="addForm.status" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="开始时间">
                    <el-input v-model="addForm.startTime" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="结束时间">
                    <el-input v-model="addForm.endTime" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="金额">
                    <el-input v-model="addForm.amount" autocomplete="off" />
                </el-form-item>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="dialogAddVisible = false">取消添加</el-button>
                    <el-button type="primary" @click="handleAdd">
                        确认添加
                    </el-button>
                </div>
            </template>
        </el-dialog>
        <div class="overflow-x-auto">
            <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
                <thead>
                    <tr>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider whitespace-nowrap">
                            订单号
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider whitespace-nowrap">
                            用户ID
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider whitespace-nowrap">
                            设备ID
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider whitespace-nowrap">
                            状态
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider whitespace-nowrap">
                            开始时间
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider whitespace-nowrap">
                            结束时间
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider whitespace-nowrap">
                            金额
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider whitespace-nowrap">
                            操作
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-if="orders.length === 0">
                        <td colspan="7" class="text-center py-3 px-5 border-b border-gray-200 text-red-500">
                            请求服务器失败
                        </td>
                    </tr>
                    <tr v-else v-for="order in orders" :key="order.orderId"
                        class="bg-white hover:bg-gray-100 transition-colors duration-200 text-sm">
                        <td class="py-3 px-5 border-b border-gray-200">{{ order.orderNumber }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ order.userId }}</td>
                        <td class="py-3 px-5 border-b border-gray-200 overflow-x-auto" 
                            style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                            {{ order.deviceId }}
                        </td>
                        <td class="py-3 px-5 border-b border-gray-200" style="white-space: nowrap;">
                            {{ getStatusText(order.status) }}
                        </td>
                        <td class="py-3 px-5 border-b border-gray-200" style="white-space: nowrap;">
                            {{ order.startTime }}
                        </td>
                        <td class="py-3 px-5 border-b border-gray-200" style="white-space: nowrap;">
                            {{ order.endTime }}
                        </td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ order.amount }}</td>
                        <td class="py-3 px-5 border-b border-gray-200 flex space-x-3">
                            <button plain @click="handleEdit(order.orderId)"
                                class="text-sm border text-blue-500 bg-blue-100 px-4 py-2 rounded hover:bg-blue-200 transition-colors duration-200">Edit</button>
                            <button @click="handleDelete(order.orderId)"
                                class="text-sm bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 transition-colors duration-200">Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>


<script setup>
import fetchRequest from '@api';
import { onMounted, ref } from 'vue';

const orders = ref([]);
const dialogUpdateVisible = ref(false);
const dialogAddVisible = ref(false);

const updateForm = ref({
    orderNumber: '',
    userId: null,
    deviceId: null,
    status: '',
    startTime: '',
    endTime: '',
    amount: 0.0,
    isscanqrcode: null
});

const addForm = ref({
    orderNumber: '',
    userId: null,
    deviceId: null,
    status: '',
    startTime: '',
    endTime: '',
    amount: 0.0,
    isscanqrcode: null
});

onMounted(async () => {
    handleRefresh();
});

const handleEdit = async (orderId) => {
    const res = await fetchRequest('/admin/order/getById/' + orderId);
    console.log(res);
    updateForm.value = res.data;
    dialogUpdateVisible.value = true;
};

const handleUpdate = async () => {
    const res = await fetchRequest('/admin/order/update', {
        method: 'POST',
        body: updateForm.value
    });
    if (res.code === 200) {
        ElMessage.success('修改成功');
    } else {
        ElMessage.error('修改失败');
    }
    dialogUpdateVisible.value = false;
    handleRefresh();
};

const handleAdd = async () => {
    const res = await fetchRequest('/admin/order/save', {
        method: 'POST',
        body: addForm.value
    });
    if (res.code === 200) {
        ElMessage.success('添加成功');
    } else {
        ElMessage.error('添加失败');
    }
    dialogAddVisible.value = false;
    handleRefresh();
};

const handleDelete = async (orderId) => {
    const res = await fetchRequest('/admin/order/delete/' + orderId);
    if (res.code === 200) {
        ElMessage.success('删除成功');
    } else {
        ElMessage.error('删除失败');
    }
    handleRefresh();
};

const getStatusText = (status) => {
    switch (status) {
        case 'isComplete':
            return '完成';
        case 'isGoing':
            return '进行中';
        case 'unpaid':
            return '未支付';
        default:
            return '未知状态';
    }
};

const handleRefresh = async () => {
    const res = await fetchRequest('/admin/order/get');
    orders.value = res.data.sort((a, b) => new Date(b.startTime) - new Date(a.startTime));
}
</script>

