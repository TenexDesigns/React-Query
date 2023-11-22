React query provides a caching for 5 minites, with the kesy of the uniwur key e.g superhereos and the call back function passed to the useQury


 const { isLoading, error, isError,data } = useQuery('super-hero', fetchData);

        REat query also provides a background refetch for the same qury to avoid stale data



CUSTOMISE CCHING TIME



 const { isLoading, error, isError,data } = useQuery('super-hero', fetchData, { cacheTime: 5000});










  
