<template>
    <div class="container mx-auto p-6 bg-white-100 min-h-screen">
        <h1 class="text-3xl font-bold mb-6 text-center text-gray-800">设备管理</h1>

        <div class="flex justify-end mb-4">
            <button @click="dialogAddVisible = true"
                class="bg-cyan-500 hover:bg-cyan-600 text-white px-4 py-2 rounded transition-colors duration-200">
                添加设备
            </button>
        </div>
        <el-dialog v-model="dialogUpdateVisible" title="修改设备" width="500">
            <el-form :model="updateForm">
                <el-form-item label-position="left" label-width="80" label="设备ID">
                    <el-input v-model="updateForm.deviceId" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="状态">
                    <el-input v-model="updateForm.status" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="每小时费率">
                    <el-input v-model="updateForm.hourlyRate" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="位置">
                    <el-input v-model="updateForm.location" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="图片">
                    <el-input v-model="updateForm.imgsrc" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="距离">
                    <el-input v-model="updateForm.distance" autocomplete="off" />
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
        <el-dialog v-model="dialogAddVisible" title="添加设备" width="500">
            <el-form :model="addForm">
                <el-form-item label-position="left" label-width="80" label="设备ID">
                    <el-input v-model="addForm.deviceId" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="状态">
                    <el-input v-model="addForm.status" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="每小时费率">
                    <el-input v-model="addForm.hourlyRate" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="位置">
                    <el-input v-model="addForm.location" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="图片">
                    <el-input v-model="addForm.imgsrc" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="距离">
                    <el-input v-model="addForm.distance" autocomplete="off" />
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
                    <tr class="bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider">
                        <th class="py-3 px-5 border-b-2 border-gray-200 whitespace-nowrap">设备ID</th>
                        <th class="py-3 px-5 border-b-2 border-gray-200 whitespace-nowrap">状态</th>
                        <th class="py-3 px-5 border-b-2 border-gray-200 whitespace-nowrap">每小时费率</th>
                        <th class="py-3 px-5 border-b-2 border-gray-200 whitespace-nowrap">位置</th>
                        <th class="py-3 px-5 border-b-2 border-gray-200 whitespace-nowrap">图片</th>
                        <th class="py-3 px-5 border-b-2 border-gray-200 whitespace-nowrap">距离</th>
                        <th class="py-3 px-5 border-b-2 border-gray-200 whitespace-nowrap">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-if="devices.length === 0">
                        <td colspan="7" class="text-center py-3 px-5 border-b border-gray-200 text-red-500">
                            请求服务器失败
                        </td>
                    </tr>
                    <tr v-else v-for="device in devices" :key="device.id"
                        class="bg-white hover:bg-gray-100 transition-colors duration-200">
                        <td class="py-3 px-5 border-b border-gray-200">{{ device.deviceId }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ device.status }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ device.hourlyRate }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ device.location }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">
                            <img :src="device.imgsrc" alt="设备图片" class="w-16 h-16 object-cover" />
                        </td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ device.distance }}</td>
                        <td class="py-3 px-5 border-b border-gray-200 flex space-x-3">
                            <button plain @click="handleEdit(device.id)"
                                class="text-sm border text-blue-500 bg-blue-100 px-4 py-2 rounded hover:bg-blue-200 transition-colors duration-200">Edit</button>
                            <button @click="handleDelete(device.id)"
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

const devices = ref([]);
const dialogUpdateVisible = ref(false);
const dialogAddVisible = ref(false);

const updateForm = ref({
    deviceId: '',
    status: '',
    hourlyRate: 0.0,
    location: '',
    imgsrc: '',
    distance: ''
});

const addForm = ref({
    deviceId: '',
    status: '',
    hourlyRate: 0.0,
    location: '',
    imgsrc: '',
    distance: ''
});

onMounted(async () => {
    const res = await fetchRequest('/admin/device/get');
    console.log(res);
    devices.value = res.data;
});

const handleEdit = async (deviceId) => {
    const res = await fetchRequest('/admin/device/getById/' + deviceId);
    console.log(res);
    updateForm.value = res.data;
    dialogUpdateVisible.value = true;
};

const handleUpdate = async () => {
    const res = await fetchRequest('/admin/device/update', {
        method: 'POST',
        body: updateForm.value
    });
    if (res.code === 200) {
        ElMessage.success('修改成功');
    } else {
        ElMessage.error('修改失败');
    }
    dialogUpdateVisible.value = false;
    const deviceRes = await fetchRequest('/admin/device/get');
    devices.value = deviceRes.data;
};

const handleAdd = async () => {
    const res = await fetchRequest('/admin/device/save', {
        method: 'POST',
        body: addForm.value
    });
    if (res.code === 200) {
        ElMessage.success('添加成功');
    } else {
        ElMessage.error('添加失败');
    }
    dialogAddVisible.value = false;
    const deviceRes = await fetchRequest('/admin/device/get');
    devices.value = deviceRes.data;
};

const handleDelete = async (deviceId) => {
    const res = await fetchRequest('/admin/device/delete/' + deviceId);
    if (res.code === 200) {
        ElMessage.success('删除成功');
    } else {
        ElMessage.error('删除失败');
    }
    const deviceRes = await fetchRequest('/admin/device/get');
    devices.value = deviceRes.data;
};
</script>

