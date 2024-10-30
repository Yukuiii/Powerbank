import {
    createWebHistory,
    createRouter
} from 'vue-router'
const routes = [{
    name: 'home',
    path: '/', 
    redirect: '/login'
}, {
    name: 'login',
    path: '/login',
    component: () => import('@/admin/login.vue'),
}, {
    name: 'user_rentsuccess',
    path: '/user/rentsuccess',
    component: () => import('@/views/user_rentsuccess.vue')
}, {
    name: 'dashboard',
    path: '/dashboard',
    component: () => import('@/admin/dashboard.vue'),
    children: [{
        name: 'home',
        path: '',
        component: () => import('@/admin/home.vue'),
    }, {
        name: 'users',
        path: 'users',
        component: () => import('@/admin/users.vue'),
    }, {
        name: 'devices',
        path: 'devices',
        component: () => import('@/admin/devices.vue'),
    }, {
        name: 'orders',
        path: 'orders',
        component: () => import('@/admin/orders.vue'),
    }]
}, {
    name: 'user_login',
    path: '/user/login',
    component: () => import('@/user/user_login.vue')
}, {
    name: 'index',
    path: '/user',
    component: () => import('@/user/index.vue'),
    children: [{
        name: 'user_index',
        path: '',
        component: () => import('@/user/user_index.vue')
    }, {
        name: 'user_device',
        path: 'device',
        component: () => import('@/user/user_device.vue')
    }, {
        name: 'user_order',
        path: 'order',
        component: () => import('@/user/user_order.vue')
    }, {
        name: 'user_profile',
        path: 'profile',
        component: () => import('@/user/user_profile.vue')
    }, {
        name: 'user_recharge',
        path: 'recharge',
        component: () => import('@/user/user_recharge.vue')
    }, {
        name: 'user_profile_update',
        path: 'profile_update',
        component: () => import('@/user/user_profile_update.vue')
    }]
}]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
