job('Seed Job') {
  wrappers {
    preBuildCleanup()
  }
  scm {
    git {
      branch('master')
      remote {
        name('upstream')
        // replace this with whever you put this repo
        url('https://github.com/junjiejia/job-dsl/master/')
      }
    }
  }
  steps {
    // running tests first prevents half deployments which could break
    gradle 'clean test'
    dsl {
      // any job ending in Job.groovy will be deployed
      external 'jobs/piplineJob-dsl.groovy'
    }
  }
}
