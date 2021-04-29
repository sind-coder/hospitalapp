<template>
 <div>
  <patients-list :patients="patients" />
 </div>
</template>

<script>
    import PatientsList from 'components/PatientsList.vue'
    export default {
        components: {
        PatientsList,
        },
        props: ['patients']
  }
</script>

<style>

</style>