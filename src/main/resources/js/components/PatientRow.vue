<template>
    <div>
        <b-card :title='patient.lastName' :sub-title="patient.firstName" class="mb-2 text-info" border-variant="info">
            <b-card-text  class="text-info">
                Diagnosis: {{patient.diagnosis}}
            </b-card-text>
            <b-card-text class="text-info">
                Age: {{patient.age}}
            </b-card-text>
            <b-button variant="outline-danger" v-b-tooltip.bottom title="Delete patient's record" @click="del">Delete</b-button>

        </b-card>
    </div>
</template>

<script>
    export default {
        props: ['patient','patients'],
       methods:{
        del() {
            this.$resource('/patient{/id}').remove({id: this.patient.id}).then(result => {
                if (result.ok) {
                    this.patients.splice(this.patients.indexOf(this.patient), 1)
                }
            })
        }
}
  }
</script>
<style>

</style>