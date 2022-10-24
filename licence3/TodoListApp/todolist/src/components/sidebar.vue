<template>
        <label>{{name}}</label>
</template>
<script>
    
    export default {
        name: 'sidebar',
        components: {
        },
        props: {
            id: {type: Number, default: 1},
            name: {type: String}
        },
    }
</script>
