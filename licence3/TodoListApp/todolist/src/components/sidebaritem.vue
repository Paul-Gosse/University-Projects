<template>
    <p class=" font-sans text-lg antialiased mr-3">{{ task }}</p>
</template>

<script>
 
export default {
        name: 'task',
        
        props:{
           id: {type: Number},
           completed: {type: Boolean},
           task: {type: String},
        }
}   
</script>
