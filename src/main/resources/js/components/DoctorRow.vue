<template>
    <div>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <b-card :title='doctor.lastName' :sub-title="doctor.firstName" class="mb-2 text-info" border-variant="info">
            <b-card-text>
                Position: {{doctor.position}}
            </b-card-text>
            <b-card-text>
                Time: с {{doctor.time_start}} до {{doctor.time_end}}
            </b-card-text>
            <b-button-group>
        <b-button variant="outline-success"
                  v-b-tooltip.bottom title="Check patient's list"
                  @click="set">Patients</b-button>
        <b-button variant="outline-danger" v-b-tooltip.bottom title="Delete doctor's record" @click="del">Delete</b-button>
            </b-button-group>
        </b-card>
    </div>
</template>

<script>
import PatientsList from 'components/PatientsList.vue'
import PatientForm from 'components/PatientForm.vue'
import Patients from 'components/Patients.vue'

    export default {
    components: {
               PatientsList,
               PatientForm,
               Patients
                            },
        props: ['doctor','doctors'],
data() {
    return {
    patients: '',
    id: ''
    }},
methods:{
del() {
            this.$resource('/doctor{/id}').remove({id: this.doctor.id}).then(result => {
                if (result.ok) {
                    this.doctors.splice(this.doctors.indexOf(this.doctor), 1)
                }
            })
        },
set: function() {
this.$router.push({ name: 'patient',params: {id : this.doctor.id}});
},
doctorMethod: function(id) {
this.id = this.doctor.id;
console.log(this.id);
return { id: this.id }
}
  }}
</script>

<style>

</style>