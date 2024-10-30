<template>
    <div class="container mx-auto p-6 bg-white-100 min-h-screen">
        <h1 class="text-3xl font-bold mb-6 text-center text-gray-800">用户管理</h1>

        <div class="flex justify-end mb-4">
            <button @click="dialogAddVisible = true"
                class="bg-cyan-500 hover:bg-cyan-600 text-white px-4 py-2 rounded transition-colors duration-200">
                添加用户
            </button>
        </div>
        <el-dialog v-model="dialogUpdateVisible" title="修改信息" width="500">
            <el-form :model="updateForm">
                <el-form-item label-position="left" label-width="80" label="用户名">
                    <el-input v-model="updateForm.username" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="邮箱">
                    <el-input v-model="updateForm.email" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="余额">
                    <el-input v-model="updateForm.balance" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="注册时间">
                    <el-input v-model="updateForm.registrationTime" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="头像链接">
                    <el-input v-model="updateForm.avatarimgsrc" autocomplete="off" />
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
        <el-dialog v-model="dialogAddVisible" title="添加用户" width="500">
            <el-form :model="addForm">
                <el-form-item label-position="left" label-width="80" label="用户名">
                    <el-input v-model="addForm.username" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="邮箱">
                    <el-input v-model="addForm.email" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="余额">
                    <el-input v-model="addForm.balance" autocomplete="off" />
                </el-form-item>
                <el-form-item label-position="left" label-width="80" label="头像链接">
                    <el-input v-model="addForm.avatarimgsrc" autocomplete="off" />
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
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider">
                            用户名
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider">
                            密码
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider">
                            邮箱
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider">
                            余额
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider">
                            注册时间
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider">
                            头像
                        </th>
                        <th
                            class="py-3 px-5 border-b-2 border-gray-200 bg-gray-200 text-left text-sm font-semibold text-gray-700 uppercase tracking-wider">
                            操作
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-if="users.length === 0">
                        <td colspan="7" class="text-center py-3 px-5 border-b border-gray-200 text-red-500">
                            请求服务器失败
                        </td>
                    </tr>
                    <tr v-else v-for="user in users" :key="user.userId"
                        class="bg-white hover:bg-gray-100 transition-colors duration-200">
                        <td class="py-3 px-5 border-b border-gray-200">{{ user.username }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ user.password }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ user.email }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ user.balance }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">{{ user.registrationTime }}</td>
                        <td class="py-3 px-5 border-b border-gray-200">
                            <img :src="user.avatarimgsrc || 'https://ae01.alicdn.com/kf/Sba7971d8859a4546808f220b6dd34a51f.jpeg'"
                                alt="Avatar" class="w-10 h-10 rounded-full">
                        </td>
                        <td class="py-3 px-5 border-b border-gray-200">
                            <button plain @click="handleEdit(user.userId)"
                                class="text-sm border text-blue-500 bg-blue-100 px-4 py-2 rounded hover:bg-blue-200 transition-colors duration-200">Edit</button>
                            <button @click="handleDelete(user.userId)"
                                class="text-sm bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 transition-colors duration-200 ml-3">Delete</button>
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
const users = ref([]);
const dialogUpdateVisible = ref(false)
const dialogAddVisible = ref(false)

const updateForm = ref({
    username: '',
    password: '',
    email: '',
    balance: 0.00,
    registrationTime: '',
    avatarimgsrc: ''
})

const addForm = ref({
    username: '',
    password: '',
    email: '',
    balance: 0.00,
    avatarimgsrc: ''
})

onMounted(async () => {
    const res = await fetchRequest('/admin/user/get');
    console.log(res);
    users.value = res.data;
});

//异步修改获取用户信息
const handleEdit = async (userId) => {
    const res = await fetchRequest('/admin/user/getById/' + userId);
    console.log(res);
    updateForm.value = res.data;
    dialogUpdateVisible.value = true;
}

const handleUpdate = async () => {
    const res = await fetchRequest('/admin/user/update', {
        method: 'POST',
        body: updateForm.value
    });
    if (res.code === 200) {
        ElMessage.success('修改成功');
    } else {
        ElMessage.error('修改失败');
    }
    dialogUpdateVisible.value = false;
    const userRes = await fetchRequest('/admin/user/get');
    users.value = userRes.data;
}

const handleAdd = async () => {
    const res = await fetchRequest('/admin/user/save', {
        method: 'POST',
        body: addForm.value
    });
    if (res.code === 200) {
        ElMessage.success('添加成功');
    } else {
        ElMessage.error('添加失败');
    }
    dialogAddVisible.value = false;
    const userRes = await fetchRequest('/admin/user/get');
    users.value = userRes.data;
}

const handleDelete = async (userId) => {
    const res = await fetchRequest('/admin/user/delete/' + userId);
    if (res.code === 200) {
        ElMessage.success('删除成功');
    } else {
        ElMessage.error('删除失败');
    }
    const userRes = await fetchRequest('/admin/user/get');
    users.value = userRes.data;
}
</script>

