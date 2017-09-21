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
            <nuxt-link class="btn btn-primary" title="Vraag een demo versie aan" :to="'#demo'">Vraag een demo versie aan</nuxt-link>
            <nuxt-link class="btn btn-secundary hidden-xs" title="Deel met jouw school" :to="'#delen'">Deel met jouw school</nuxt-link>
            <h4>Licht jouw school in door dit te delen.</h4>
          </div>

        </div>
      </div>
    </section>

  </div>
</template>


<script>
import MastheadNavigation from '../components/MastheadNavigation.vue'
export default {
  components: {
    MastheadNavigation
  }
}
</script>
