<template>
    <div class="bg-gray-100 h-screen flex flex-col">
        <div class="flex-grow overflow-auto mb-14">
            <router-view></router-view>
        </div>
    </div>
    <navigationbar/>
</template>

<script setup>
import navigationbar from '@/components/navigationbar.vue';

</script>