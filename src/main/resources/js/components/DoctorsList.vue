<template>
    <div>
        <b-button block class="mb-2" @click="create" variant="outline-info">Add new doctor</b-button>
    <doctor-row v-for="doctor in doctors" :key="doctor.id" :doctor="doctor" :doctors="doctors" />
    </div>
</template>

<script>
import DoctorRow from 'components/DoctorRow.vue'
import DoctorForm from 'components/DoctorForm.vue'
    export default {
    components: {
               DoctorRow,
               DoctorForm
                            },
        props: ['doctors'],
        data() {
        return {
        doctor: null
        }},
        created(){
        this.$resource('/doctor{/id}').get().then(result =>
        result.json().then(data =>
        data.forEach(doctor => this.doctors.push(doctor))))
},
methods: {
create: function(){
var id = this.$route.params.id
this.$router.push({ name: 'createDoctor',params: {id : id}});}
    }
  }
</script>

<style>

</style>