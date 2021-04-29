<template>
    <div id="app">
        <h3 class="text-info" >Hospital</h3>
        <b-nav tabs justified>
            <b-nav-item href="/">Doctor's list</b-nav-item>
            <b-nav-item href="/about">About project</b-nav-item>
        </b-nav>
        <router-view></router-view>
    </div>
</template>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
<script>
    import DoctorsList from 'components/DoctorsList.vue'
    import PatientsList from 'components/PatientsList.vue'
    export default {
        components: {
        DoctorsList,
        PatientsList
        },
        data() {
            return {
            doctors: [
            ],
            patients: [ ]
            }
  }
  }
</script>

<style>

</style>