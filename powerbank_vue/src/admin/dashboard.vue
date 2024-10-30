<template>
    <div class="flex">
        <Siderbar />
        <div class="flex-1 ml-64">
            <router-view></router-view>
        </div>
    </div>
</template>

<script setup>
import Siderbar from '@/components/siderbar.vue'
</script>

