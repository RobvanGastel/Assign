<template>
	<div class="row component-masthead">

    <masthead-navigation></masthead-navigation>

    <section class="masthead section-padding">
			<div class="container">
				<div class="row">

					<div class="col-md-12 col-lg-10 col-lg-offset-1 text-center">
            <h1>Assign maakt samen leren leuk en makkelijk</h1>
          </div>
          <div class="col-md-12 col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2 text-center">
            <h3>Elke leerling heeft unieke eigenschappen en kennis die een ander niet heeft. Met Assign brengen wij leerlingen bij elkaar om elkaar te helpen en van elkaar te leren.</h3>
            <button class="btn btn-primary" title="Vraag een demo versie aan" @click="showModal = true">Vraag een demo versie aan</button>
            <button v-clipboard:copy="clipboardMessage" class="btn btn-secundary hidden-xs" title="Deel de gekopieerde link met jouw school" v-on:click="setNewValue">{{ btnMessage }}</button>
            <h4>Licht jouw school in door dit te delen.</h4>
          </div>
        </div>
      </div>
    </section>

		<modal-license v-if="showModal" @close="showModal = false"></modal-license>

  </div>
</template>

<script>
import MastheadNavigation from './MastheadNavigation'
import ModalLicense from '../ModalLicense'
export default {
  components: {
    MastheadNavigation,
    ModalLicense
  },
  data: () => ({
    showModal: false,
    clipboardMessage: 'https://assignapp.nl/voor-scholen',
    btnMessage: 'Deel met jouw school'
  }),
  methods: {
    setNewValue: () => {
      this.btnMessage = 'De link is gekopieerd'
    }
  }
}
</script>
