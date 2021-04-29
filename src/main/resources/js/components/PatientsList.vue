<template>
 <div>
 <b-button block class="mb-2" variant="outline-info" @click="create">Add new patient</b-button>
 <patient-row v-for="patient in patients" :key="patient.id" :patient="patient" :patients="patients"/>
 </div>
</template>

<script>
import PatientRow from 'components/PatientRow.vue'
import PatientForm from 'components/PatientForm.vue'
    export default {
    components: {
               PatientRow,
               PatientForm
                            },
        props: ['doctor','doctors'],
data() {
    return {
    patients: ''
    }},
created(){
var id = this.$route.params.id;
console.log(id);
this.$resource('/doctor{/id}').get({id: id}).then(result => result.json().then(data => {this.patients = data;console.log(data);}))},
 methods: {
 create: function(){
 var id = this.$route.params.id
 this.$router.push({ name: 'createPatient',params: {id : id}});}
 }
}
</script>

<style>

</style>