<template>
	<div class="row component-masthead">

    <masthead-navigation></masthead-navigation>
    <section class="masthead section-padding">
			<div class="container">
				<div class="row">

					<div class="col-md-12 col-lg-10 col-lg-offset-1 text-center">
            <h1>Het middel voor een communicatieve leeromgeving</h1>
          </div>
          <div class="col-md-12 col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2 text-center">
            <h3>Assign helpt bij het creëren van een communicatieve leeromgeving waar studenten zelfstandig en samenwerkend leren doormiddel van een iOS App.</h3>
            <button class="btn btn-primary" title="Vraag een demo versie aan" @click="showModal = true">Vraag een demo versie aan</button>
            <modal-banner v-if="showModal" @close="showModal = false"></modal-banner>
            <nuxt-link class="btn btn-secundary hidden-xs" title="Deel met jouw school" :to="'#delen'">Deel met jouw school</nuxt-link>
            <h4>Licht jouw school in door dit te delen.</h4>
          </div>

        </div>
      </div>
    </section>

  </div>
</template>

<script>
import MastheadNavigation from './MastheadNavigation'
import ModalBanner from '../modal/ModalBanner'
export default {
  components: {
    MastheadNavigation,
    ModalBanner
  },
  data: () => ({
    showModal: false
  })
}
</script>
