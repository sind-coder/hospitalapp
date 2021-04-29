<template>
    <div>
        <doctors-list :doctors="doctors" />
    </div>
</template>

<script>
    import DoctorsList from 'components/DoctorsList.vue'
    export default {
        components: {
        DoctorsList,
        },
        data() {
            return {
            doctors: [
            ]
            }
  }
  }
</script>

<style>

</style>